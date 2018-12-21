

## Rakenne



![Pakkauskaavio](https://github.com/aoskarih/ot_harjoitustyo/blob/master/docs/Pakkauskaavio.png)


## Käyttöliittymä

Käyttöliittymässä on kaksi eri näkymää. Perusnäkymä, jossa kaikki toiminnallisuus tapatuu ja näkymä, jossa voi valita uuden systeemitiedoston.

Käyttöliittymä ei ole täysin erillään sovelluslogiikasta. Käyttöliittymä joutuu pitämään listan systeemeistä ja lisäksi tekemään tarvittaessa Place objekteja eri käyttöliittymän objektejen perusteella, jotka se voi sitten laittaa itse sovelluslogiikan metodeihin parametreinä.


## Päätoiminnallisuudet
Kuvataan sovelluksen päätoiminnallisuudet sekvenssikaavioina.

#### &Delta;v:n laskeminen
Kaikki toiminnallisuus lähtee siitä, että Main-luokan main-metodi avaa käyttöliittymän MainWindow.

Käyttöliittymässä on kaksi objektia, jotka suorittavat laskemisen. Nappi calculateDV, jota painamalla laskeminen suoritetaan, ja answerDV tekstikenttä, joka näyttää tuloksen.

Itse laskeminen tapahtuu kun calculateDV nappi kutsuu DeltaVCalc luokan staattista metodia fromAToB. Metodin parametreinä on kaksi paikkaa (Place), a ja b. Nappi luo paikat a ja b käyttöliittymän muiden objektien määräämien muuttujien arvojen avulla.

Metodi fromAToB  käyttää luokkien DeltaVCalc ja PhysicsEquations eri metodeja hieman tilanteesta riippuen ja palauttaa double arvon napille calculateDV, joka sitten asettaa sen tekstikentän arvoksi.

![Sekvenssikaavio](https://github.com/aoskarih/ot_harjoitustyo/blob/master/docs/DeltaSekvenssi.png)



#### Matka-ajan laskeminen

Matka-ajan laskeminen tapahtuu lähes identtisesti &Delta;v:n laskemisen kanssa. Ainut ero on itse laskujen suorituksessa, joka tapahtuu luokassa TravelTimeCalc, joka käyttää hyväkseen metodeja myös luokista DeltaVCalc ja PhysicsEquations.

Lisäksi matka-ajalla on oma nappi, calculateTime ja oma tekstikenttä answerTime.

![Sekvenssikaavio](https://github.com/aoskarih/ot_harjoitustyo/blob/master/docs/TimeSekvenssi.png)

#### Uuden systeemin lisääminen

Uuden systeemin lisääminen alkaa painamalla nappia addSystem. Tämä avaa MainWindow luokassa olevan uuden FileChooser ikkunan, jossa voi valita tiedoston. Sitten valittu tiedosto annetaan luokan SystemFileReader metodille getNewSystem joka ensin lukee tiedoston ja laittaa datan ohjelmalle hyödylliseen muuttujaan, joka sitten annetaan metodille decode, joka tulkitsee datan ja tekee sen pohjalta uuden systeemin, joka palautetaan addSystem napille, joka lisää sen MainWindow luokassa olevaan listaan systeemejä. Tämän jälkeen nappi vielä päivittää muut käyttöliittymän objektit, jotka riippuvat systeemien listasta.

![Sekvenssikaavio](https://github.com/aoskarih/ot_harjoitustyo/blob/master/docs/newSystemSekvenssi.png)

## Sovellukseen jääneet heikkoudet

Sovellus ei rajoita, mitä arvoja eri metodeille voi antaa, joten sovellus olettaa että annetut arvot ovat järkeviä.
