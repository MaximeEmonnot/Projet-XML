<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:output method="html" />
    
    <xsl:template match="/">
        <html lang="fr">
            <head>
                <meta charset="UTF-8"/>
                <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <link rel="stylesheet" href="style.css"/>
                <title>Document</title>
            </head>
            <body>
                <section class="articles">
                    <xsl:for-each select="animaux/animal">
                        <article>
                            <div class="article-wrapper">
                                <figure>
                                    <img src="{gif/@src}" alt="" height="280" width="280"/>
                                </figure>
                                <div class="article-body">
                                    <h2><xsl:value-of select="@nom"/></h2>
                                    <a href="{@nom}.html" class="read-more">
                                        Plus d'info
                                        <svg xmlns="http://www.w3.org/2000/svg" class="icon" viewBox="0 0 20 20" fill="currentColor">
                                            <path fill-rule="evenodd" d="M12.293 5.293a1 1 0 011.414 0l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-2.293-2.293a1 1 0 010-1.414z" clip-rule="evenodd" />
                                        </svg>
                                    </a>
                                </div>
                            </div>
                        </article>
                    </xsl:for-each>
                </section>
            </body>
        </html>
    </xsl:template>
    
</xsl:stylesheet>