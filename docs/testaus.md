# Testaus

Ohjelmalle on tehty yksikkö ja integraatiotestejä JUnitilla ja käyttöliittymä sekä asennus on testattu manuaalisesti.

## Yksikkö- ja integraatiotestaus

Pakkauksen calculating luokkia testaa pääasiassa integraatiotestit DeltaVCalcTest ja TravelTimeTest, mutta luokalle GravitationalSystem tehtiin muutama yksikkötesti. Pakkauksen operating luokalle SystemFileReader tehtiin myös muutama yksikkötesti.

Testikattavuus raportti saatiin jacocolla ja testauksen rivikattavuus on 88% ja haarautumakattavuus 93%. Lukuun ottamatta käyttöliittymän luokkia.

![Testikattavuus](https://github.com/aoskarih/ot_harjoitustyo/blob/master/docs/TestiKattavuus.png)

Testejen ulkopuolelle jäi SystemFileReader luokan metodi, joka lukee tiedoston sisällön, sekä yksi metodi luokasta PhysicsEquations, jolle ei ole tällähetkellä käyttöä sovelluksessa, mutta joka on hyvä kuitenkin pitää mukana. Lisäksi suuri osa rivikattavuuden vajavaisuudesta tulee siitä, että kattavuuteen tulee mukaan rivi, jossa luokka aloitetaan, eikä tätä riviä voi testata.

## Käyttöliittymä

Käyttöliittymässä on käyty läpi kaikki päätoiminnallisuudet sekä testattu kaikki ilmiselvät heikkoudet, eivätkä ne ole tuottaneet ongelmia.

## Asennus

Sovelluksen haettu ja testattu README:n ohjaamalla tavalla Linuxissa.

## Tunnetut heikkoudet

- Jos uutta tiedostoa valittaessa valitaan tiedosto, joka ei ole oikean muotoinen sovellus lisää tyhjän systeemin, eikä anna raporttia epäonnistuneesta lisäyksestä.
