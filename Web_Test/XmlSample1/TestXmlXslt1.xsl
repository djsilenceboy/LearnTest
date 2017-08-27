<?xml version="1.0"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="students">
	<html>
	<body>
		<table border="1">
			<tr>
				<th>Name</th>
				<th>Age</th>
				<th>Weight</th>
				<th>Score</th>
			</tr>
			<xsl:apply-templates/>
		</table>
	</body>
	</html>
</xsl:template>

<xsl:template match="student">
	<tr>
		<td><xsl:value-of select="name"/></td>
		<td><xsl:apply-templates select="age"/></td>
		<td><xsl:apply-templates select="weight"/></td>
		<td><xsl:value-of select="score"/></td>
	</tr>
</xsl:template>

<xsl:template match="age">
	<xsl:value-of select="."/>
	<xsl:text> </xsl:text>
	<xsl:value-of select="@unit"/>
</xsl:template>

<xsl:template match="weight">
	<xsl:value-of select="."/>
	<xsl:text> </xsl:text>
	<xsl:value-of select="@unit"/>
</xsl:template>

</xsl:stylesheet> 
