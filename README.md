# Avaruusmatkalaskuri

Sovelluksella voi laskea erinäisiä arvoja, jotka liittyvät avaruusaluksen matkaan paikasta A paikkaan B.

## Dokumentaatio

[käyttöohje](https://github.com/aoskarih/ot_harjoitustyo/blob/master/docs/kayttoohje.md)

[vaatimusmäärittely](https://github.com/aoskarih/ot_harjoitustyo/blob/master/docs/vaatimusmaarittely.md)

[tuntikirjanpito](https://github.com/aoskarih/ot_harjoitustyo/blob/master/docs/tuntikirjanpito.md)

[arkkitehtuuri](https://github.com/aoskarih/ot_harjoitustyo/blob/master/docs/arkkitehtuuri.md)

[testaus](https://github.com/aoskarih/ot_harjoitustyo/blob/master/docs/testaus.md)

## Komentorivitoiminnot

Kaikki komennot kannattaa suorittaa sijainnissa .../Avaruusmatkalaskuri

### Suoritus
Joko suoraan komennolla

    mvn compile exec:java -Dexec.mainClass=spacetravelcalc.calculating.Main

Tai ensin tekemällä .jar tiedoston komennolla ja sitten suorittamalla sen

    mvn package
    java -jar Syntyneen-tiedoston-nimi.jar

### Testaus

Testien suoritus:

    mvn test

Testiraportti:

    mvn jacoco:report

Checkstyle:

    mvn jxr:jxr checkstyle:checkstyle

Sekä jacoco- että checkstyleraportti generoituu sijainnin target/site/ alle. Jacocolla saattaa olla oma kansio.

### JavaDoc

JavaDocin saa generoitua komennolla

    mvn javadoc:javadoc

JavaDoc generoituu todennäköisesti sijaintiin target/site/apidocs/
