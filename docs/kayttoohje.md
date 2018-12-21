# Käyttöohje

## Perus käyttö

Ensin valitaa systeemi, missä laskut halutaan suorittaa. Systeemin voi valita ylimmästä dropdown-valikosta.

Sitten valitaan lähtöpaikka ja kohde. Tämä tapahtuu valitsemalla kierrettävä kappale ja kiertoradan korkeus. Kierrettävät kappaleet saa dropdown-valikoista. Kiertoradan korkeuden voi syöttää tekstikenttään. Ohjelma olettaa korkeuden yksiköksi metrejä.

Sitten laskut voi suorittaa oikeassa laidassa olevilla napeilla.

## Systeemin lisäys tiedostolla

Uuden systeemin voi lisätä painamalla nappia "Lisää uusi systeemi" ja valitsemalla sopivan tiedoston. Kun tiedosto on valittu, uusi systeemi ilmestyy systeemi-valikkoon.

Jotta sovellus osaa lukea tiedostoa täytyy sen olla muotoa:

    t;keskus kappaleen nimi;massa;kappaleen säde;systeemin nimi
    c;kiertävän kappaleen nimi;massa;kappaleen säde;kiertoradan säde;kierrettävän kappaleen nimi
    c;kiertävän kappaleen nimi;massa;kappaleen säde;kiertoradan säde;kierrettävän kappaleen nimi
    c; ...

Missä ensimmäisen rivin kappale on koko systeemin keskuskappale, joka ei kierrä muita kappaleita. Lisäksi listassa täytyy määritellä kierrettävä kappale aina ennen kiertolaista. Siis esimerkiksi:

    t;aurinko;...
    c;kuu;...
    c;maa;...

ei ole pätevä, vaan järjestyksen pitää olla

    t;aurinko;...
    c;maa;...
    c;kuu;...

koska kuu kiertää maata. Lisäksi on suositeltavaa, että kaikille kappaleille annetaa eri nimi vaikka se ei suoraan aiheutakkaan ongelmia.
