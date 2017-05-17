package com.jason.se.secrity;

import com.google.common.io.BaseEncoding;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.*;

/*
<table border="1"><tr><td>Name</td><td>Encode, 100 bytes</td><td>Decode, 100 bytes</td><td>Encode, 1000 bytes</td><td>Decode, 1000 bytes</td><td>Encode, 200000000 bytes</td><td>Decode, 200000000 bytes</td></tr>
<tr><td>ApacheImpl</td><td>4.411 sec</td><td>4.237 sec</td><td>2.057 sec</td><td>2.004 sec</td><td>1.949 sec</td><td>2.226 sec</td></tr>
<tr><td>IHarderImpl</td><td>0.852 sec</td><td>1.999 sec</td><td>0.775 sec</td><td>1.895 sec</td><td>0.744 sec</td><td>1.893 sec</td></tr>
<tr><td>Java8Impl</td><td>0.528 sec</td><td>0.91 sec</td><td>0.436 sec</td><td>0.816 sec</td><td>0.415 sec</td><td>0.792 sec</td></tr>
<tr><td>MiGBase64Impl</td><td>0.695 sec</td><td>1.6 sec</td><td>0.614 sec</td><td>1.457 sec</td><td>0.596 sec</td><td>1.455 sec</td></tr>
</table>
<table border="1"><tr><td>Name</td><td>Encode, 100 bytes</td><td>Decode, 100 bytes</td><td>Encode, 1000 bytes</td><td>Decode, 1000 bytes</td><td>Encode, 200000000 bytes</td><td>Decode, 200000000 bytes</td></tr>
<tr><td>ApacheImpl</td><td>4.113 sec</td><td>4.733 sec</td><td>2.308 sec</td><td>2.667 sec</td><td>2.205 sec</td><td>2.552 sec</td></tr>
<tr><td>GuavaImpl</td><td>3.305 sec</td><td>4.178 sec</td><td>3.12 sec</td><td>3.755 sec</td><td>3.102 sec</td><td>3.744 sec</td></tr>
<tr><td>IHarderImpl</td><td>1.179 sec</td><td>2.645 sec</td><td>1.012 sec</td><td>2.439 sec</td><td>0.976 sec</td><td>2.431 sec</td></tr>
<tr><td>Java8Impl</td><td>0.808 sec</td><td>1.101 sec</td><td>0.712 sec</td><td>0.913 sec</td><td>0.699 sec</td><td>0.887 sec</td></tr>
<tr><td>JavaXmlImpl</td><td>0.721 sec</td><td>1.067 sec</td><td>0.664 sec</td><td>0.947 sec</td><td>0.689 sec</td><td>0.885 sec</td></tr>
<tr><td>MiGBase64Impl</td><td>0.887 sec</td><td>2.113 sec</td><td>0.788 sec</td><td>1.978 sec</td><td>0.812 sec</td><td>1.928 sec</td></tr>
<tr><td>SunImpl</td><td>11.153 sec</td><td>8.281 sec</td><td>3.992 sec</td><td>4.533 sec</td><td>3.289 sec</td><td>4.096 sec</td></tr>
</table>
 */
public class Base64Tests {
    private static final int TOTAL_BUFFER_SIZE = 200 * 1000 * 1000;
    private static final Base64Codec[] m_codecs = { new GuavaImpl(), new MiGBase64Impl(), new JavaXmlImpl(),
             new SunImpl(),  new IHarderImpl(), new ApacheImpl(),  };
    // new Java8Impl(),
    private static final Base64ByteCodec[] m_byteCodecs = {
        new ApacheImpl(), new IHarderImpl(), new MiGBase64Impl()
    };
    // new Java8Impl(),
    
    public static void main( final String... args ) throws IOException, InterruptedException {
        //testMaxBufferSize();
        //System.exit(0);

        //bytes
        Map<Integer, Map<String, TestResult>> results = new HashMap<Integer, Map<String, TestResult>>( 2 );
        results.put(100, testBytes(100));
        results.put(1000, testBytes(1000));
        results.put(TOTAL_BUFFER_SIZE, testBytes(TOTAL_BUFFER_SIZE));

        System.out.println( formatResults( results ) );

        //string
        results = new HashMap<Integer, Map<String, TestResult>>( 2 );
        results.put(100, testString(100));
        results.put(1000, testString(1000));
        results.put(TOTAL_BUFFER_SIZE, testString(TOTAL_BUFFER_SIZE));

        System.out.println( formatResults( results ) );
    }

    private static void testMaxBufferSize()
    {
        final Random r = new Random( 125 ); //seed is set to make results reproducible
        for ( final Base64Codec codec : m_codecs )
        {
            for ( int i = 1; i <= 17; ++i ) {
                byte[] data = new byte[ i * 100 * 1000 * 1000 ];
                r.nextBytes( data );
                try
                {
                    codec.encode( data );
                }
                catch (Throwable ex)
                {
                    data = null;
                    System.out.println( "Codec " + codec.getClass().getName() + " Failed encoding at size = " + i/10.0 + " Gb due to " + ex );
                    ex.printStackTrace();
                    break;
                }
            }
            for ( int i = 1; i <= 17; ++i ) {
                byte[] data = new byte[ i * 100 * 1000 * 1000 ];
                r.nextBytes( data );
                String encoded = null;
                try
                {
                    encoded = codec.encode( data );
                }
                catch (Throwable ex)
                {
                    data = null;
                    System.out.println( "Codec " + codec.getClass().getName() + " Failed encoding at size = " + i/10.0 + " Gb due to " + ex );
                    ex.printStackTrace();
                    break;
                }
                try
                {
                    data = null;
                    codec.decode( encoded );
                }
                catch (Throwable ex)
                {
                    encoded = null;
                    System.out.println( "Codec " + codec.getClass().getName() + " Failed decoding at size = " + i/30.0*4 + " Gb due to " + ex );
                    ex.printStackTrace();
                    break;
                }
            }
        }
    }


    private static Map<String, TestResult> testString(final int bufferSize) throws IOException, InterruptedException {
        final Random r = new Random( 125 ); //seed is set to make results reproducible
        final List<byte[]> buffers = new ArrayList<byte[]>( TOTAL_BUFFER_SIZE / bufferSize );
        for ( int i = 0; i < TOTAL_BUFFER_SIZE / bufferSize; ++i )
        {
            final byte[] buf = new byte[ bufferSize ];
            r.nextBytes( buf );
            buffers.add( buf );
        }

        //warmup
        for ( final Base64Codec codec : m_codecs )
        {
            testStringCodec(codec, buffers);
            System.gc();
        }

        //actual run
        final Map<String, TestResult> results = new HashMap<String, TestResult>( 5 );
        for ( final Base64Codec codec : m_codecs )
        {
            final String name = codec.getClass().getName();
            results.put(name.substring(name.indexOf('$') + 1), testStringCodec(codec, buffers));
            System.gc();
        }

        return results;
    }

    private static Map<String, TestResult> testBytes( final int bufferSize ) throws IOException, InterruptedException {
        final Random r = new Random( 125 ); //seed is set to make results reproducible
        final List<byte[]> buffers = new ArrayList<byte[]>( TOTAL_BUFFER_SIZE / bufferSize );
        for ( int i = 0; i < TOTAL_BUFFER_SIZE / bufferSize; ++i )
        {
            final byte[] buf = new byte[ bufferSize ];
            r.nextBytes( buf );
            buffers.add( buf );
        }

        //warmup
        for ( final Base64ByteCodec codec : m_byteCodecs )
        {
            testByteCodec(codec, buffers);
            System.gc();
        }

        //actual run
        final Map<String, TestResult> results = new HashMap<String, TestResult>( 5 );
        for ( final Base64ByteCodec codec : m_byteCodecs )
        {
            final String name = codec.getClass().getName();
            results.put(name.substring(name.indexOf('$') + 1), testByteCodec(codec, buffers));
            System.gc();
        }

        return results;
    }

    private static String formatResults( final Map<Integer, Map<String, TestResult>> results )
    {
        //sorted keys for pretty printing
        final List<Integer> sortedKeys = new ArrayList<Integer>( results.keySet() );
        Collections.sort( sortedKeys );
        final List<String> sortedTests = new ArrayList<String>( results.get( sortedKeys.get( 0 ) ).keySet() );
        Collections.sort( sortedTests );

        //header
        final StringBuilder sb = new StringBuilder( 2048 );
        sb.append("<table border=\"1\"><tr><td>Name</td>");
        for ( final Integer size : sortedKeys )
            sb.append( "<td>Encode, " + size + " bytes</td><td>Decode, " + size + " bytes</td>" );
        sb.append("</tr>\n");
        //body
        for ( final String test : sortedTests )
        {
            sb.append( "<tr><td>" + test + "</td>" );
            for ( final Integer size : sortedKeys )
            {
                final TestResult sizeResults = results.get( size ).get( test );
                sb.append("<td>" + sizeResults.encodeTime + " sec</td><td>" + sizeResults.decodeTime + " sec</td>" );
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>");
        return sb.toString();
    }


    private static class TestResult
    {
        public final double encodeTime;
        public final double decodeTime;

        private TestResult(double encodeTime, double decodeTime) {
            this.encodeTime = encodeTime;
            this.decodeTime = decodeTime;
        }
    }

    private static TestResult testStringCodec(final Base64Codec codec, final List<byte[]> buffers) throws IOException {
        final List<String> encoded = new ArrayList<String>( buffers.size() );
        final long start = System.currentTimeMillis();
        for ( final byte[] buf : buffers )
            encoded.add( codec.encode( buf ) );
        final long encodeTime = System.currentTimeMillis() - start;

        final List<byte[]> result = new ArrayList<byte[]>( buffers.size() );
        final long start2 = System.currentTimeMillis();
        for ( final String s : encoded )
            result.add( codec.decode( s ) );
        final long decodeTime = System.currentTimeMillis() - start2;

        for ( int i = 0; i < buffers.size(); ++i )
        {
            if ( !Arrays.equals( buffers.get( i ), result.get( i ) ) )
                System.out.println( "Diff at pos = " + i );
        }
        return new TestResult( encodeTime / 1000.0, decodeTime / 1000.0 );
    }

    private static TestResult testByteCodec( final Base64ByteCodec codec, final List<byte[]> buffers ) throws IOException {
        final List<byte[]> encoded = new ArrayList<byte[]>( buffers.size() );
        final long start = System.currentTimeMillis();
        for ( final byte[] buf : buffers )
            encoded.add( codec.encodeBytes(buf) );
        final long encodeTime = System.currentTimeMillis() - start;

        final List<byte[]> result = new ArrayList<byte[]>( buffers.size() );
        final long start2 = System.currentTimeMillis();
        for ( final byte[] ar : encoded )
            result.add( codec.decodeBytes(ar) );
        final long decodeTime = System.currentTimeMillis() - start2;

        for ( int i = 0; i < buffers.size(); ++i )
        {
            if ( !Arrays.equals( buffers.get( i ), result.get( i ) ) )
                System.out.println( "Diff at pos = " + i );
        }
        return new TestResult( encodeTime / 1000.0, decodeTime / 1000.0 );
    }

    private static interface Base64Codec
    {
        public String encode( final byte[] data );
        public byte[] decode( final String base64 ) throws IOException;
    }

    private static interface Base64ByteCodec
    {
        public byte[] encodeBytes( final byte[] data );
        public byte[] decodeBytes( final byte[] base64 ) throws IOException;
    }

    /**
    //available in Java 8 only!
    private static class Java8Impl implements Base64Codec, Base64ByteCodec
    {
        private final java.util.Base64.Decoder m_decoder = java.util.Base64.getDecoder();
        private final java.util.Base64.Encoder m_encoder = java.util.Base64.getEncoder();

        @Override
        public String encode(byte[] data) {
            return m_encoder.encodeToString(data);
        }

        @Override
        public byte[] decode(String base64) throws IOException {
            return m_decoder.decode(base64);
        }

        public byte[] encodeBytes(byte[] data) {
            return m_encoder.encode( data );
        }

        public byte[] decodeBytes(byte[] base64) throws IOException {
            return m_decoder.decode( base64 );
        }
    }
    **/

    private static class JavaXmlImpl implements Base64Codec  //no byte[] implementation
    {
        public String encode(byte[] data) {
            return DatatypeConverter.printBase64Binary( data );
        }

        public byte[] decode(String base64) throws IOException {
            return DatatypeConverter.parseBase64Binary( base64 );
        }
    }

    private static class ApacheImpl implements Base64Codec, Base64ByteCodec
    {
        public String encode(byte[] data) {
            return org.apache.commons.codec.binary.Base64.encodeBase64String(data);
        }

        public byte[] decode(String base64) {
            return org.apache.commons.codec.binary.Base64.decodeBase64( base64 );
        }

        public byte[] encodeBytes(byte[] data) {
            return org.apache.commons.codec.binary.Base64.encodeBase64( data );
        }

        public byte[] decodeBytes(byte[] base64) throws IOException {
            return org.apache.commons.codec.binary.Base64.decodeBase64( base64 );
        }
    }

    private static class IHarderImpl implements Base64Codec, Base64ByteCodec
    {
        public String encode(byte[] data) {
            return net.iharder.Base64.encodeBytes(data);
        }

        public byte[] decode(String base64) throws IOException {
            return net.iharder.Base64.decode( base64, net.iharder.Base64.DONT_GUNZIP );
        }

        public byte[] encodeBytes(byte[] data) {
            return net.iharder.Base64.encodeBytesToBytes( data );
        }

        public byte[] decodeBytes(byte[] base64) throws IOException {
            return net.iharder.Base64.decode( base64 );
        }
    }

    private static class SunImpl implements Base64Codec //no byte[] impl
    {
        private final sun.misc.BASE64Encoder encoder = new BASE64Encoder();
        private final sun.misc.BASE64Decoder decoder = new BASE64Decoder();

        public String encode(byte[] data) {
            return encoder.encode( data );
        }

        public byte[] decode(String base64) throws IOException {
            return decoder.decodeBuffer( base64 );
        }
    }

    private static class MiGBase64Impl implements Base64Codec, Base64ByteCodec
    {
        public String encode(byte[] data) {
        	
            return com.migcomponents.migbase64.Base64.encodeToString(data, false);
        }

        public byte[] decode(String base64) throws IOException {
            return com.migcomponents.migbase64.Base64.decode( base64 );
        }

        public byte[] encodeBytes(byte[] data) {
            return com.migcomponents.migbase64.Base64.encodeToByte(data, false);
        }

        public byte[] decodeBytes(byte[] base64) throws IOException {
            return com.migcomponents.migbase64.Base64.decode( base64 );
        }
    }

    private static class GuavaImpl implements Base64Codec //no byte[] version
    {
        private final BaseEncoding m_base64 = BaseEncoding.base64();

        public String encode(byte[] data) {
            return m_base64.encode(data);
        }

        public byte[] decode(String base64) throws IOException {
            return m_base64.decode( base64 );
        }
    }

}
