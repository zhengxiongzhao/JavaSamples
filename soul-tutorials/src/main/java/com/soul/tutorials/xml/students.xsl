<?xml version="1.0"  encoding="GB2312"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
<html>
<head><title>ѧ����Ϣ</title></head>
<body>
    <table border="1">
    <tr>
        <th>����</th>
        <th>����</th>
        <th>�绰</th>
    </tr>

    <xsl:for-each select="ѧ��������/ѧ��">
        <tr>
            <td><xsl:value-of select="����"/></td>
            <td><xsl:value-of select="����"/></td>
            <td><xsl:value-of select="�绰"/></td>
        </tr>
    </xsl:for-each>
    </table>

</body></html>
</xsl:template>
</xsl:stylesheet>