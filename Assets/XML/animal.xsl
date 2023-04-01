<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:output method="html" />
    
    <xsl:template match="/animal">
        <head>
            <meta charset="UTF-8"/>
            <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
            <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
            <link rel="stylesheet" href="style_animal.css"/>
            <link rel="preconnect" href="https://fonts.googleapis.com"/>
            <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin=""/>
            <link href="https://fonts.googleapis.com/css2?family=Roboto" display="swap" rel="stylesheet"/>
            <title><xsl:value-of select="@nom"/></title>
        </head>
        <body>
            <div class="wrapper">
                <div class="nom"><xsl:value-of select="@nom"/></div>
                <div class="classification">
                    <p><strong>Classification:</strong></p>
                    <ul class="animation">
                        <li><strong>Embranchement:</strong>&#160;&#160;<xsl:value-of select="classification/embranchement/text()"/></li>
                        <li><strong>Classe:</strong>&#160;&#160;<xsl:value-of select="classification/classe/text()"/></li>
                        <li><strong>Ordre:</strong>&#160;&#160;<xsl:value-of select="classification/ordre/text()"/></li>
                        <li><strong>Famille:</strong>&#160;&#160;<xsl:value-of select="classification/famille/text()"/></li>
                        <li><strong>Genre:</strong>&#160;&#160;<xsl:value-of select="classification/genre/text()"/></li>
                        <li><strong>Nom scientifique:</strong>&#160;&#160;<xsl:value-of select="classification/nom_scientifique/text()"/></li>
                    </ul>
                </div>
                <div class="caracteristique">
                    <p><strong>Caracteristique:</strong></p>
                    <ul class="animation">
                        <li><strong>Longueur:</strong>&#160;&#160;<xsl:value-of select="caracteristique/longueur_max/text()"/>&#160;<xsl:value-of select="caracteristique/longueur_max/@unite"/></li>
                        <li><strong>Poids:</strong>&#160;&#160;<xsl:value-of select="caracteristique/poids_max/text()"/>&#160;<xsl:value-of select="caracteristique/poids_max/@unite"/></li>
                        <li><strong>Longevite:</strong>&#160;&#160;<xsl:value-of select="caracteristique/longevite_max/text()"/>&#160;<xsl:value-of select="caracteristique/longevite_max/@unite"/></li>
                        <li><strong>Type de pea:</strong>&#160;&#160;<xsl:value-of select="caracteristique/type_peau/text()"/></li>
                        <li><strong>Regime alimentaire:</strong>&#160;&#160;<xsl:value-of select="caracteristique/regime/text()"/></li>
                        <li><strong>Vitesse:</strong>&#160;&#160;<xsl:value-of select="caracteristique/vitesse_max/text()"/>&#160;<xsl:value-of select="caracteristique/vitesse_max/@unite"/></li>
                    </ul>
                </div>
                <div class="localisation">
                    <xsl:for-each select="localisations/localisation">
                        <xsl:choose>
                            <xsl:when test="text() = 'Afrique'">
                                <div class="point afrique"></div>
                            </xsl:when>
                            <xsl:when test="text() = 'Asie'">
                                <div class="point asie"></div>
                            </xsl:when>
                            <xsl:when test="text() = 'Europe'">
                                <div class="point europe"></div>
                            </xsl:when>
                            <xsl:when test="text() = 'Amérique du Nord'">
                                <div class="point amerique_du_nord"></div>
                            </xsl:when>
                            <xsl:when test="text() = 'Amérique du Sud'">
                                <div class="point amerique_du_sud"></div>
                            </xsl:when>
                            <xsl:when test="text() = 'Océanie'">
                                <div class="point oceanie"></div>
                            </xsl:when>
                            <xsl:when test="text() = 'Antarctique'">
                                <div class="point antarctique"></div>
                            </xsl:when>
                        </xsl:choose>
                    </xsl:for-each>
                </div>
                <div class="media">
                    <img src="../.{gif/@src}" height="220"/>
                    <audio id="cri" src="../.{caracteristique/cri/@src}" preload="auto"></audio>
                        <button onclick="document.getElementById('cri').play();">jouer le cri</button>
                </div>
            </div>
        </body>
    </xsl:template>
</xsl:stylesheet>