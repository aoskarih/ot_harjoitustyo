# OT harjoitustyö


## Dokumentaatio


[vaatimusmäärittely](https://github.com/aoskarih/ot_harjoitustyo/blob/master/docs/vaatimusmaarittely.md)

[tuntikirjanpito](https://github.com/aoskarih/ot_harjoitustyo/blob/master/docs/tuntikirjanpito.md)

[arkkitehtuuri](https://github.com/aoskarih/ot_harjoitustyo/blob/master/docs/arkkitehtuuri.md)

## Komentorivitoiminnot

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

Labtoolissa oli palautteena, että testikattavuusraportin generointi ei onnistu. Minulla ei ole sen kanssa ollut ongelmia.

Checkstyle testaus onnistuu komennolla

    mvn jxr:jxr checkstyle:checkstyle
