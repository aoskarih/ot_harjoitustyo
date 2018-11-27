# OT harjoitustyö


## Dokumentaatio


[vaatimusmäärittely](https://github.com/aoskarih/ot_harjoitustyo/blob/master/docs/vaatimusmaarittely.md)

[tuntikirjanpito](https://github.com/aoskarih/ot_harjoitustyo/blob/master/docs/tuntikirjanpito.md)

[arkkitehtuuri](https://github.com/aoskarih/ot_harjoitustyo/blob/master/docs/arkkitehtuuri.md)

## Komentorivitoiminnot

### Suoritus

    mvn compile exec:java -Dexec.mainClass=spacetravelcalc.calculating.SpaceTravelCalc

### Testaus

Testien suoritus:

    mvn test

Testiraportti:

    mvn jacoco:report

Labtoolissa oli palautteena, että testikattavuusraportin generointi ei onnistu. Minulla ei ole sen kanssa ollut ongelmia.
