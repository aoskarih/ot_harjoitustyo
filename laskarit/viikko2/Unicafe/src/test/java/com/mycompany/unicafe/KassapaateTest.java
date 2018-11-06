package com.mycompany.unicafe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hyarhyar
 */
public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        
    }
    
    @Test
    public void arvotAlussaOikein() {
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateismaksuToimii() {
        paate.syoEdullisesti(240);
        assertEquals(100240, paate.kassassaRahaa());
        paate.syoMaukkaasti(400);
        assertEquals(100640, paate.kassassaRahaa());
    }
    
    @Test
    public void kateismaksuPalauttaaOikein() {
        assertEquals(200, paate.syoEdullisesti(200));
        assertEquals(60 , paate.syoEdullisesti(300));
        
        assertEquals(300, paate.syoMaukkaasti(300));
        assertEquals(100, paate.syoMaukkaasti(500));
    }
    
    @Test
    public void kateismaksussaRahaaEiSynnyTyhjasta() {
        paate.syoEdullisesti(200);
        paate.syoMaukkaasti(300);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void myytyjenLounaidenMaaraKasvaa() {
        kortti = new Maksukortti(10000);
        paate.syoEdullisesti(500);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
        paate.syoEdullisesti(kortti);
        assertEquals(2, paate.edullisiaLounaitaMyyty());
        paate.syoMaukkaasti(1000);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
        paate.syoMaukkaasti(kortti);
        assertEquals(2, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiMaksuToimiiEdullinen() {
        kortti = new Maksukortti(1000);
        assertEquals(true, paate.syoEdullisesti(kortti));
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void korttiMaksuToimiiMaukas() {
        kortti = new Maksukortti(1000);
        assertEquals(true, paate.syoMaukkaasti(kortti));
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void kortiltaEiVelotetaTurhaan() {
        kortti = new Maksukortti(100);
        paate.syoEdullisesti(kortti);
        assertEquals(100, kortti.saldo());
        paate.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void korttimaksuPalauttaaOikein() {
        kortti = new Maksukortti(100);
        assertEquals(false, paate.syoMaukkaasti(kortti));
        assertEquals(false, paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void kassanRahamaaraEiMuutu() {
        kortti = new Maksukortti(1000);
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    
    }
    
    @Test
    public void myytyjaLounaitaEiTuleTurhaan() {
        kortti = new Maksukortti(100);
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortinLatausToimii() {
        kortti = new Maksukortti(0);
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, paate.kassassaRahaa());
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaSaldoa() {
        kortti = new Maksukortti(100);
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(100, kortti.saldo());
    }
    
}
