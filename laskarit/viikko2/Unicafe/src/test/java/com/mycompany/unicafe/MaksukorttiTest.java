package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void saldoKirjoitetaanOikein() {
        kortti = new Maksukortti(150);
        assertEquals("saldo: 1.50", kortti.toString());
    
    }
    
    @Test
    public void lataaminenToimii() {
        kortti.lataaRahaa(90);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void saldoVahenee() {
        kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());
    }
    
    @Test
    public void saldoEiVaheneLiikaa() {
        kortti.otaRahaa(50);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void otaRahaaPalauttaaTrue() {
        assertEquals(true, kortti.otaRahaa(5));
    }
    
    @Test
    public void otaRahaaPalauttaaFalse() {
        assertEquals(false, kortti.otaRahaa(100));
    }
}
