<?xml version="1.0"  encoding="GB2312"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
<html>
<head><title>学生信息</title></head>
<body>
    <table border="1">
    <tr>
        <th>姓名</th>
        <th>年龄</th>
        <th>电话</th>
    </tr>

    <xsl:for-each select="学生花名册/学生">
        <tr>
            <td><xsl:value-of select="姓名"/></td>
            <td><xsl:value-of select="年龄"/></td>
            <td><xsl:value-of select="电话"/></td>
        </tr>
    </xsl:for-each>
    </table>

</body></html>
</xsl:template>
</xsl:stylesheet>