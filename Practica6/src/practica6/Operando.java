/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Hector
 */
public class Operando extends Practica6{
    
    
    
    String[] Direccion(String Operando,String dir, int lin,String moddir,String codop,int BanOrg,String ContLoc,String FCC, String moddir2,String FCC3, String ContLoc2){
        String[] Resultado = new String[] {"null","null","null","0000","0000","null","0000"};
        String[] Busqueda = new String[] {"null","null"};
        String  b=".err",Mdir="null", Res="null";
        String Byte="null";
        int BanContLoc=0;
        int x=0,y=0,z=0; 
        boolean banRel=false,MaqBan=false;
        try{
        File f =new File(dir+b);
        FileWriter fw=new FileWriter(f,true);
        BufferedWriter error=new BufferedWriter(fw);
        
           // System.out.println("Codop antes: "+codop);
         // System.out.println("Operando mod antes: "+Operando+" moddir: "+moddir);
         
      if(Operando.matches("^[a-zA-Z]{0,8}[^;]{0,1}[\\w]$")||Operando.matches("^\\$[0-9A-Fa-f]*")||Operando.matches("^\\@[0-7].*")||Operando.matches("^\\%.*")||Operando.matches("^\\#.*")||Operando.matches("^[0-9].*")||Operando.matches("^[a-zA-Z].*")||Operando.matches("^\\[.*\\]$")||Operando.matches("^\\-.*")||Operando.matches("^\\,.*")||codop.equals("FCC")){
          /*   
          
        */  
         codop=codop.toUpperCase();
          //System.out.println("Codop mod: "+codop);
         // System.out.println("A2: "+z);
        // System.out.println("Operando mod Despues: "+Operando+" moddir: "+moddir);
         
         ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               /////////////////////////////////////////////////////ContLoc
              //////////////////////////////////////////////////////////ORG
               
              if(codop.equals("ORG")){
                  int ORG=0; 
                  if(BanOrg!=1){
                   System.out.println("ORG: "+ORG+"ContLoc"+ContLoc);
                  if(Operando.matches("^\\$[0-9A-Fa-f]*")||Operando.matches("^\\@[0-7]+")||Operando.matches("^\\%[10]*$")){
                      //Entra Hexadecimal
                      if(Operando.matches("^\\$.*")){
                      int siz=Operando.length();
                        String Hexa=Operando.substring(1,siz);
                           ORG=Integer.parseInt(Hexa,16);
                          if(ORG<=65535){
                               //inserta el valor en Hexadecimal al Contador Logico
                              ContLoc=Integer.toHexString(ORG).toUpperCase();
                              ContLoc2=ContLoc;
                              BanOrg=1;
                              Mdir="DTV";
                              }
                             }//termina Hexadecimal
                            //Empieza Octal
                      if(Operando.matches("^\\@.*")){
                      int siz=Operando.length();
                        String Hexa=Operando.substring(1,siz);
                           ORG=Integer.parseInt(Hexa,8);
                          if(ORG<=65535){
                               //inserta el valor en Hexadecimal al Contador Logico
                              ContLoc=Integer.toHexString(ORG).toUpperCase(); 
                              ContLoc2=ContLoc;
                              BanOrg=1;
                              Mdir="DTV";
                              }
                             }//termina Octal
                            //Empieza Binario
                       if(Operando.matches("^\\%.*")){
                      int siz=Operando.length();
                        String Hexa=Operando.substring(1,siz);
                           ORG=Integer.parseInt(Hexa,2);
                          if(ORG<=65535){
                               //inserta el valor en Hexadecimal al Contador Logico
                              ContLoc=Integer.toHexString(ORG).toUpperCase(); 
                              BanOrg=1;
                              }
                             }//termina Binario
                               }else{//sin base
                                  if(Operando.matches("^[0-9]*")){
                                   ORG=Integer.parseInt(Operando);
                                   if(ORG<=65535){
                                   ContLoc=Integer.toHexString(ORG).toUpperCase();
                                   ContLoc2=ContLoc;
                                  Mdir="DTV";
                                     BanOrg=1; 
                                   }
                                  }
                              else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+ORG+")");
                               error.newLine();
                                  }
                          }
              }
                  BanContLoc=1;
              }//Termina ORG
              /////////////////////EQU
              if(codop.equals("EQU")){
                  int EQU=0;
                  
                  if(Operando.matches("^\\$[0-9A-Fa-f]*")||Operando.matches("^\\@[0-7]+")||Operando.matches("^\\%[10]*$")){
                      
                  //Entra Hexa
                  if(Operando.matches("^\\$.*")){
                      
                      int siz=Operando.length();
                        String Hexa=Operando.substring(1,siz);
                           EQU=Integer.parseInt(Hexa,16);
                          if(EQU<=65535){
                               //inserta el valor en Hexadecimal al Contador Logico
                              ContLoc=Integer.toHexString(EQU).toUpperCase(); 
                              ContLoc2=ContLoc;
                              Mdir="DTV";
                              }
                             }//termina hexadecimal
                  
                  //Inicia Octal
                  if(Operando.matches("^\\@.*")){
                      
                      int siz=Operando.length();
                        String Hexa=Operando.substring(1,siz);
                           EQU=Integer.parseInt(Hexa,8);
                          if(EQU<=65535){
                               //inserta el valor en Hexadecimal al Contador Logico
                              ContLoc=Integer.toHexString(EQU).toUpperCase(); 
                              ContLoc2=ContLoc;
                              Mdir="DTV";
                              }
                             }//Termina Octal
                  //Inicia Binario
                   if(Operando.matches("^\\%.*")){
                      
                      int siz=Operando.length();
                        String Hexa=Operando.substring(1,siz);
                           EQU=Integer.parseInt(Hexa,2);
                          if(EQU<=65535){
                               //inserta el valor en Hexadecimal al Contador Logico
                              ContLoc=Integer.toHexString(EQU).toUpperCase();
                              ContLoc2=ContLoc;
                              Mdir="DTV";
                              }
                             }//Termina Binario
                             }else{//sin base
                                  if(Operando.matches("^[0-9]?[0-9]*")){
                                   EQU=Integer.parseInt(Operando);
                                   if(EQU<=65535){
                                   ContLoc=Integer.toHexString(EQU).toUpperCase();
                                   ContLoc2=ContLoc;
                                    Mdir="DTV"; 
                                   }
                                  }
                              else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+EQU+")");
                               error.newLine();
                                  }
                          }
                  
                  BanContLoc=1;
              }//Termina EQU
              ////////////////////////Directivas de constantes
              ////////////////////////DW, DB, DC.W, DC.B, FCB, FDB, FCC
              if(codop.equals("DW")||codop.equals("DB")||codop.equals("DC.W")||codop.equals("DC.B")||codop.equals("FCB")||codop.equals("FDB")||codop.equals("FCC"))
              {
                  int DiCons=0;
                  //De un Byte
                  //System.out.println("sin base db fuera"+Operando+"codop: "+codop);
                  if(codop.equals("DB")||codop.equals("DC.B")||codop.equals("FCB"))
                  {
                     // System.out.println("sin base db dentro"+Operando);
                      if(Operando.matches("^\\$[0-9A-Fa-f]*")||Operando.matches("^\\@[0-7]+")||Operando.matches("^\\%[10]*$"))
                      {
                          //entra Hexadecimal
                      if(Operando.matches("^\\$.*"))
                       {
                         int siz=Operando.length();
                        String Hexa=Operando.substring(1,siz);
                           DiCons=Integer.parseInt(Hexa,16);
                           if(DiCons<=255&&DiCons>=0){
                               //suma 1 l contloc
                               int cont=Integer.parseInt(ContLoc,16);
                               cont=cont+1;
                               ContLoc=Integer.toHexString(cont).toUpperCase(); 
                               Mdir="DTV";
                           }else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+DiCons+")");
                               error.newLine();
                           }
                       }//termina Hexadecimal
                      //entra octal
                      if(Operando.matches("^\\@.*"))
                       {
                         int siz=Operando.length();
                        String Oct=Operando.substring(1,siz);
                           DiCons=Integer.parseInt(Oct,8);
                           if(DiCons<=255&&DiCons>=0){
                               //suma 1 l contloc
                               int cont=Integer.parseInt(ContLoc,16);
                               cont=cont+1;
                               ContLoc=Integer.toHexString(cont).toUpperCase(); 
                               Mdir="DTV";
                           }else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+DiCons+")");
                               error.newLine();
                           }
                       }//termina octal
                      //entra Binario
                      if(Operando.matches("^\\%.*"))
                       {
                         int siz=Operando.length();
                        String Hexa=Operando.substring(1,siz);
                           DiCons=Integer.parseInt(Hexa,2);
                           if(DiCons<=255&&DiCons>=0){
                               //suma 1 al contloc
                               int cont=Integer.parseInt(ContLoc,16);
                               cont=cont+1;
                               ContLoc=Integer.toHexString(cont).toUpperCase(); 
                               Mdir="DTV";
                           }else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+DiCons+")");
                               error.newLine();
                           }
                       }//termina Binario
                      }else{//sin base
                         // System.out.println("sin base db2 "+Operando);
                          if(Operando.matches("^[0-9]?[0-9]*")){
                              
                          DiCons=Integer.parseInt(Operando,10);
                           if(DiCons<=255&&DiCons>=0){
                               //suma 1 al contloc
                               int cont=Integer.parseInt(ContLoc,16);
                               cont=cont+1;
                               ContLoc=Integer.toHexString(cont).toUpperCase(); 
                               Mdir="DTV";
                           }else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+DiCons+")");
                               error.newLine();
                           }
                          }else{
                               error.write("Linea: "+lin+" Error: "+codop+" no se encontro un valor: "+DiCons);
                               error.newLine();
                                  }
                      }
                  }//termina de un byte
                  //empieza de dos bytes
                  if(codop.equals("DW")||codop.equals("DC.W")||codop.equals("FDB"))
                  {
                      
                      if(Operando.matches("^\\$[0-9A-Fa-f]*")||Operando.matches("^\\@[0-7]+")||Operando.matches("^\\%[10]*$"))
                      {
                          //entra Hexadecimal
                      if(Operando.matches("^\\$.*"))
                       {
                         int siz=Operando.length();
                        String Hexa=Operando.substring(1,siz);
                           DiCons=Integer.parseInt(Hexa,16);
                           if(DiCons<=65535&&DiCons>=0){
                               //suma 2 a contloc
                               int cont=Integer.parseInt(ContLoc,16);
                               cont=cont+2;
                               ContLoc=Integer.toHexString(cont).toUpperCase(); 
                               Mdir="DTV";
                           }else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+DiCons+")");
                               error.newLine();
                           }
                       }//termina Hexadecimal
                      //entra octal
                      if(Operando.matches("^\\@.*"))
                       {
                         int siz=Operando.length();
                        String Oct=Operando.substring(1,siz);
                           DiCons=Integer.parseInt(Oct,8);
                           if(DiCons<=65535&&DiCons>=0){
                               //suma 2 a contloc
                               int cont=Integer.parseInt(ContLoc,16);
                               cont=cont+2;
                               ContLoc=Integer.toHexString(cont).toUpperCase(); 
                               Mdir="DTV";
                           }else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+DiCons+")");
                               error.newLine();
                           }
                       }//termina octal
                      //entra Binario
                      if(Operando.matches("^\\%.*"))
                       {
                         int siz=Operando.length();
                        String Hexa=Operando.substring(1,siz);
                           DiCons=Integer.parseInt(Hexa,2);
                           if(DiCons<=65535&&DiCons>=0){
                               //suma 2 a contloc
                               int cont=Integer.parseInt(ContLoc,16);
                               cont=cont+2;
                               ContLoc=Integer.toHexString(cont).toUpperCase(); 
                               Mdir="DTV";
                           }else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+DiCons+")");
                               error.newLine();
                           }
                       }//termina Binario
                      }else{//sin base
                          if(Operando.matches("^[0-9]?[0-9]*")){
                          DiCons=Integer.parseInt(Operando,10);
                           if(DiCons<=65535&&DiCons>=0){
                               //suma 2 al contloc
                               int cont=Integer.parseInt(ContLoc,16);
                               cont=cont+2;
                               ContLoc=Integer.toHexString(cont).toUpperCase(); 
                               Mdir="DTV";
                           }else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+DiCons+")");
                               error.newLine();
                           }
                          }else{
                               error.write("Linea: "+lin+" Error: "+codop+" no se encontro un valor: "+DiCons);
                               error.newLine();
                                  }
                      }
                  }//termina de dos bytes
                  //Entra FCC
                  
                  if(codop.equals("FCC")){
                      if(!FCC.equals(FCC3)){
                      int siz=FCC.length();
                      if(siz>0){
                          
                         //int siz=Operando.length();
                        String FCC2=FCC.substring(0,siz-1);
                        
                       // StringTokenizer FCCx = new StringTokenizer(FCC2,"\\\"");
                           //  String FCC3=FCCx.nextToken();
                             Res=FCC2;
                                 FCC3=FCC;
                            DiCons =FCC2.length();
                          //  System.out.println("FCC: "+FCC+" FCC2: "+FCC2+" TamFCC2: "+DiCons);
                         //   System.out.println("FCC2"+FCC2+"Tam "+DiCons);
                            int cont=Integer.parseInt(ContLoc,16);
                            cont=cont+DiCons;//suma la longitud del operando
                           ContLoc=Integer.toHexString(cont).toUpperCase();
                           Mdir="DTV";
                      }else{
                      error.write("Linea: "+lin+" Error:"+codop+" no cumple con el las especificaciones: "+DiCons);
                      error.newLine();
                      }
                  }else{
                          Res="\""+FCC3;
                          Mdir="DTV";
                      }
                   
                  }//Termina FCC
                  
                  BanContLoc=1;
              }//////Terminan directivas constantes
              ////////////////////////////Directivas de reserva de espacio en memoria
              /////////////////////////////DS, DS.B, DS.W, RMB, RMW
              if(codop.equals("DS")||codop.equals("DS.B")||codop.equals("DS.W")||codop.equals("RMB")||codop.equals("RMW")){
                  int DiMem=0;
                  //De un byte
                  if(codop.equals("DS")||codop.equals("DS.B")||codop.equals("RMB")){
                      
                      if(Operando.matches("\\$[0-9A-Fa-f]*")||Operando.matches("^\\@[0-7]+")||Operando.matches("^\\%[10]*$"))
                      {
                          //entra Hexadecimal
                      if(Operando.matches("^\\$.*"))
                       {
                         int siz=Operando.length();
                        String Hexa=Operando.substring(1,siz);
                           DiMem=Integer.parseInt(Hexa,16);
                           if(DiMem<=65535&&DiMem>=0){
                               //suma resultado de operando con ContLoc
                               int cont=Integer.parseInt(ContLoc,16);
                               cont=cont+DiMem;
                               ContLoc=Integer.toHexString(cont).toUpperCase(); 
                               Mdir="DTV";
                           }else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+DiMem+")");
                               error.newLine();
                           }
                       }//termina Hexadecimal
                      //entra Octal
                      if(Operando.matches("^\\@.*"))
                       {
                         int siz=Operando.length();
                        String Oct=Operando.substring(1,siz);
                           DiMem=Integer.parseInt(Oct,8);
                           if(DiMem<=65535&&DiMem>=0){
                               //suma resultado de operando con ContLoc
                               int cont=Integer.parseInt(ContLoc,16);
                               cont=cont+DiMem;
                               ContLoc=Integer.toHexString(cont).toUpperCase(); 
                               Mdir="DTV";
                           }else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+DiMem+")");
                               error.newLine();
                           }
                       }//entra Binario
                      if(Operando.matches("^\\%.*"))
                       {
                         int siz=Operando.length();
                        String Bin=Operando.substring(1,siz);
                           DiMem=Integer.parseInt(Bin,2);
                           if(DiMem<=65535&&DiMem>=0){
                               //suma resultado de operando con ContLoc
                               int cont=Integer.parseInt(ContLoc,16);
                               cont=cont+DiMem;
                               ContLoc=Integer.toHexString(cont).toUpperCase(); 
                               Mdir="DTV";
                           }else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+DiMem+")");
                               error.newLine();
                           }
                       }
                      }else{//sin base 
                          if(Operando.matches("^[0-9]?[0-9]*")){
                          DiMem=Integer.parseInt(Operando,10);
                           if(DiMem<=65535&&DiMem>=0){
                               //suma resultado de operando con ContLoc
                               int cont=Integer.parseInt(ContLoc,16);
                               cont=cont+DiMem;
                               ContLoc=Integer.toHexString(cont).toUpperCase(); 
                               Mdir="DTV";
                           }else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+DiMem+")");
                               error.newLine();
                           }
                          }else{
                               error.write("Linea: "+lin+" Error: "+codop+" no se encontro um valor");
                               error.newLine();
                                  }
                      }
                      
                      
                  }//termina de un Byte
                  //entra de dos Bytes
                  if(codop.equals("DS.W")||codop.equals("RMW")){
                      
                      if(Operando.matches("^\\$[0-9A-Fa-f]*")||Operando.matches("^\\@[0-7]+")||Operando.matches("^\\%[10]*$"))
                      {
                          //entra Hexadecimal
                      if(Operando.matches("^\\$.*"))
                       {
                         int siz=Operando.length();
                        String Hexa=Operando.substring(1,siz);
                           DiMem=Integer.parseInt(Hexa,16);
                           if(DiMem<=65535&&DiMem>=0){
                               //suma resultado de operando*2 con ContLoc
                               int cont=Integer.parseInt(ContLoc,16);
                               DiMem=DiMem*2;
                               cont=cont+DiMem;
                               ContLoc=Integer.toHexString(cont).toUpperCase(); 
                               Mdir="DTV";
                           }else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+DiMem+")");
                               error.newLine();
                           }
                       }//termina Hexadecimal
                      //entra Octal
                      if(Operando.matches("^\\@.*"))
                       {
                         int siz=Operando.length();
                        String Oct=Operando.substring(1,siz);
                           DiMem=Integer.parseInt(Oct,8);
                           if(DiMem<=65535&&DiMem>=0){
                               
                               
                               //suma resultado de operando*2 con ContLoc
                               int cont=Integer.parseInt(ContLoc,16);
                               System.out.println("Octal: "+DiMem+" ContLoc: "+cont);
                               DiMem=DiMem*2;
                               cont=cont+DiMem;
                               ContLoc=Integer.toHexString(cont).toUpperCase(); 
                               
                               Mdir="DTV";
                           }else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+DiMem+")");
                               error.newLine();
                           }
                       }//entra Binario
                      if(Operando.matches("^\\%.*"))
                       {
                         int siz=Operando.length();
                        String Bin=Operando.substring(1,siz);
                           DiMem=Integer.parseInt(Bin,2);
                           if(DiMem<=65535&&DiMem>=0){
                               //suma resultado de operando*2 con ContLoc
                               int cont=Integer.parseInt(ContLoc,16);
                               DiMem=DiMem*2;
                               cont=cont+DiMem;
                               ContLoc=Integer.toHexString(cont).toUpperCase(); 
                               Mdir="DTV";
                           }else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+DiMem+")");
                               error.newLine();
                           }
                       }
                      }else{//sin base 
                          if(Operando.matches("^[0-9]?[0-9]*")){
                          DiMem=Integer.parseInt(Operando,10);
                           if(DiMem<=65535&&DiMem>=0){
                               //suma resultado de operando con ContLoc
                               
                               int cont=Integer.parseInt(ContLoc,16);
                               DiMem=DiMem*2;
                               System.out.println("DiMem: "+DiMem+" cont: "+cont);
                               cont=cont+DiMem;
                               System.out.println("DiMem: "+DiMem+" cont: "+cont);
                               ContLoc=Integer.toHexString(cont).toUpperCase();
                               Mdir="DTV";
                           }else{
                               error.write("Linea: "+lin+" Error:"+codop+" no cumple con el valor requerido (valor actual:"+DiMem+")");
                               error.newLine();
                           }
                          }else{
                               error.write("Linea: "+lin+" Error: "+codop+" no se encontro um valor");
                               error.newLine();
                                  }
                      }
                      
                      
                  }//Termina de dos Bytes
                  BanContLoc=1;
                  
              }//Termina Directivas  de Reserva de espacio de Memoria 
              
              
                                                                   //ContLoc/////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
         
         
         
         
          //Directo y Extendido
          if(Operando.matches("^[a-zA-Z]{0,8}[^;]{0,1}[\\w]$")||Operando.matches("^\\%[10]*$")&&BanContLoc!=1&&!codop.equals("ORG")||Operando.matches("^\\@[0-7]+")&&BanContLoc!=1&&!codop.equals("ORG")||Operando.matches("^\\$[0-9A-Fa-f]*")&&BanContLoc!=1&&!codop.equals("ORG")||Operando.matches("^[0-9]+")&&BanContLoc!=1&&!codop.equals("ORG"))
          {
          //DIR
              boolean banDir=false;
             // System.out.println("moddir "+moddir+" moddir2: "+moddir2);
              if(!moddir.equals("REL")){
             int DIR=0;
           //  System.out.println("Rel?: "+codop);
           if(!moddir2.equals(codop)&&!moddir2.equals(codop)){
               System.out.println("Entro Directo: "+codop);
              ///////////////////////////////////////////////Directo
              if(Operando.matches("^\\$[0-9A-Fa-f]*")||Operando.matches("^\\@[-]*[0-7]*")||Operando.matches("^\\%[10]*")){
                  
                  //Hexadecimal
                  if(Operando.matches("^\\$[0-9A-Fa-f]*")){
                      int tam=Operando.length();
                  String dircad=Operando.substring(1,tam);
                  DIR=Integer.parseInt(dircad,16);  
                  if(DIR>=0&&DIR<=255){
                     banDir=true; 
                  Mdir="DIR";
                  //////////////////// Busqueda de Bytes y CodMaq
                  String et="null";
                  String MaqDir= Integer.toHexString(DIR);
                  MaqDir=fillContLoc(MaqDir);//rellena los
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////
                  }
                  }//termina Hexadecimal
                  //Octal
                  if(Operando.matches("^\\@[-]*[0-7]*")){
                      int tam=Operando.length();
                  String dircad=Operando.substring(1,tam);
                  DIR=Integer.parseInt(dircad,8);  
                  if(DIR>=0&&DIR<=255){ 
                  Mdir="DIR";
                  //////////////////// Busqueda de Bytes y CodMaq
                  String et="null";
                  String MaqDir= Integer.toHexString(DIR);
                  MaqDir=fillContLoc(MaqDir);//rellena los
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////
                  }
                  banDir=true;
                  }//termina Octal
                  //entra Binario
                  if(Operando.matches("^\\%[10]*")){
                      int tam=Operando.length();
                  String dircad=Operando.substring(1,tam);
                  DIR=Integer.parseInt(dircad,2);  
                  if(DIR>=0&&DIR<=255){
                      banDir=true;
                  Mdir="DIR";
                  //////////////////// Busqueda de Bytes y CodMaq
                  String et="null";
                  String MaqDir= Integer.toHexString(DIR);
                  MaqDir=fillContLoc(MaqDir);//rellena los
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////
                  }
                  }//termina binario
              }else{
              
                  if(Operando.matches("^[0-9]*$")){
                  int tam=Operando.length();
                  String dircad=Operando.substring(0,tam);
                  DIR=Integer.parseInt(dircad,10);  
                  if(DIR>=0&&DIR<=255){
                   banDir=true;   
                  Mdir="DIR";
                  System.out.println("Operando Dir: "+Operando);
                  //////////////////// Busqueda de Bytes y CodMaq
                  String et="null";
                  String MaqDir= Integer.toHexString(DIR);
                  MaqDir=fillContLoc(MaqDir);//rellena los
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////
                  }
                  }
                  
              }//Termina Directo
              }
           
              ///////////////////////////////////////////Extendido
             // if(!moddir2.equals("DIR")){
              int EXT=0;
              if(Operando.matches("^[a-zA-Z]{1,8}[\\w]$")&&Operando.matches(".*[^,].*")&&banDir!=true||Operando.matches("^\\$[0-9A-Fa-f]*")&&banDir!=true||Operando.matches("^\\@[-]*[0-7]*")&&banDir!=true||Operando.matches("^\\%[10]*")&&banDir!=true){
                 // System.out.println("Entro EXT: codop: "+codop+" ");
                  //Hexadecimal
                  if(Operando.matches("^\\$[0-9A-Fa-f]*")){
                      int tam=Operando.length();
                  String dircad=Operando.substring(1,tam);
                  EXT=Integer.parseInt(dircad,16);  
                  if(EXT>=-32768&&EXT<=65535&&!banDir==true){
                      
                  Mdir="EXT";
                  //////////////////// Busqueda de Bytes y CodMaq
                  String et="null";
                  String MaqDir= Integer.toHexString(EXT);
                  MaqDir=fillContLoc(MaqDir);//rellena los
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////  
                  }
                  }//termina Hexadecimal
                  //Octal
                  if(Operando.matches("^\\@[-]*[0-7]*")){
                      int tam=Operando.length();
                  String dircad=Operando.substring(1,tam);
                  EXT=Integer.parseInt(dircad,8);  
                  if(EXT>=-32768&&EXT<=65535&&!banDir==true){ 
                  Mdir="EXT";
                  //////////////////// Busqueda de Bytes y CodMaq
                  String et="null";
                  String MaqDir= Integer.toHexString(EXT);
                  MaqDir=fillContLoc(MaqDir);//rellena los
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////  
                  }
                  
                  }//termina Octal
                  //entra Binario
                  if(Operando.matches("^\\%[10]*")){
                      int tam=Operando.length();
                  String dircad=Operando.substring(1,tam);
                  EXT=Integer.parseInt(dircad,2);  
                  if(EXT>=-32768&&EXT<=65535&&!banDir==true){
                     
                  Mdir="EXT";
                  //////////////////// Busqueda de Bytes y CodMaq
                  String et="null";
                  String MaqDir= Integer.toHexString(EXT);
                  MaqDir=fillContLoc(MaqDir);//rellena los
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////  
                  }
                  }//termina binario
                  //Entra Etiqueta
                  if(Operando.matches("^[a-zA-Z]{1,8}[\\w]$")&&Operando.matches(".*[^,].*")){
                      
                      //System.out.println("Entro Operando EXT: "+Operando);
                      Mdir="EXT";
                      
                      
                  }//termina Etiqueta
              }else{/////Sin base ////////////////////////////////
              //System.out.println("EXT?: "+codop);
                  if(Operando.matches("^[0-9]*$")&&banDir!=true){
                    //  System.out.println("OperandoExt: "+Operando);
                  int tam=Operando.length();
                  String dircad=Operando.substring(0,tam);
                  EXT=Integer.parseInt(dircad,10);  
                  if(EXT>=-32768&&EXT<=65535&&!banDir==true){
                     
                  Mdir="EXT";
                  //////////////////// Busqueda de Bytes y CodMaq
                  String et="null";
                  String MaqDir= Integer.toHexString(EXT);
                  MaqDir=fillContLoc(MaqDir);//rellena los
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////                  }
                  }
                  }
              }//Termina Extendido
            // }
              
             }
          
          
          }//termina Directo y Extendido
           
          ///////////////////////////////////////////////////Indexados   IDX'S
                              
          if(Operando.matches("^[-]*([0-9a-dA-D])*,[+|-]*([X|x|Y|y|sp|SP|pc|PC])*$")&&BanContLoc!=1||Operando.matches("^[-]*([0-9a-dA-D])*,([X|x|Y|y|sp|SP|pc|PC])*([+|-])*$")&&BanContLoc!=1||Operando.matches("^\\$[0-9A-Fa-f]*,([X|x|Y|y|sp|SP|pc|PC])*[+|-]*$")&&BanContLoc!=1||Operando.matches("^\\$[0-9A-Fa-f]*,([+|-])*([X|x|Y|y|sp|SP|pc|PC])*$")&&BanContLoc!=1||Operando.matches("^\\@[-]*[0-7]*,([+|-])*([X|x|Y|y|sp|SP|pc|PC])*$")&&BanContLoc!=1||Operando.matches("^\\@[-]*[0-7]*,([X|x|Y|y|sp|SP|pc|PC])*[+|-]*$")&&BanContLoc!=1||Operando.matches("^\\%[10]*,([+|-])*([X|x|Y|y|sp|SP|pc|PC])*$")&&BanContLoc!=1||Operando.matches("^\\%[10]*,([X|x|Y|y|sp|SP|pc|PC])*[+|-]*$")&&BanContLoc!=1)
          {
             // banRel=true;
              
              if(!moddir.equals("REL")){
              String IDXcad=null;
              
              StringTokenizer IDX=new StringTokenizer(Operando,",");
              
               System.out.println("Operando IDX: "+Operando);
              if(Operando.matches("^\\$[0-9A-Fa-f]*.*")||Operando.matches("^\\@[-]*[0-7]*.*")||Operando.matches("^\\%.*")){
                
                  //Entra Hexadecimal
                  if(Operando.matches("^\\$[0-9A-Fa-f]*.*")){
                      IDXcad =IDX.nextToken();
                      int Hex=IDXcad.length();
                  String IDXcade=IDXcad.substring(1,Hex);
                  // IDXcad =IDX.nextToken();
              boolean IDXB=false;    
             // IDXfirst=IDX.nextToken();
              
              if(Operando.matches("^[aAbBdD],[XxYyspSPpcPC]*")){
                  Mdir="IDX";//Acumulador
                  IDXB=true;
                  
              }
             
              if(Operando.matches("^$\\.*")&&IDXB==false){
                  
                  int  IDXint=Integer.parseInt(IDXcade,16);
                  //contienen Decimales
                  
                   // IDX 5Bits
                 if(IDXint>=-16&&IDXint<=15&&IDXB==false&&Operando.matches(".*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false){
                     IDXB=true;
                     Mdir="IDX";
                     //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir= Integer.toBinaryString(IDXint);
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  Mdir="IDX";
                  /////////////////////////////////////////////// 
                 }
                 //IDX 9 Bits
                 if(IDXint>=-256&&IDXint<=-17&&Operando.matches("^.*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false||IDXint>=16&&IDXint<=255&&Operando.matches(".*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false){
                     Mdir="IDX1";
                     IDXB=true;
                     //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir= Integer.toBinaryString(IDXint);
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  /////////////////////////////////////////////// 
                 }
                 //IDX 16Bits
                 if(IDXint<=-257&&IDXint>=-32768&&IDXB==false&&Operando.matches("^.*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false||IDXint>=256&&IDXint<=65535&&IDXB==false&&Operando.matches("^.*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false){
                     Mdir="IDX2";
                     IDXB=true;
                     //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir= Integer.toBinaryString(IDXint);
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////
                 }
                 if(IDXint<=-1&&IDXint>=-8&&Operando.matches(".*,([+|-])*([X|x|Y|y|sp|SP])*[+|-]*$")&&IDXB==false||IDXint<=8&&IDXint>=1&&Operando.matches(".*,([+|-])*([X|x|Y|y|sp|SP])*[+|-]*$")&&IDXB==false){
                     //System.out.println("Operando IDXPPP: "+Operando);
                     Mdir="IDXPP";
                     //System.out.println("Mdir Pre/Post: "+Mdir);
                     //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir= Integer.toBinaryString(IDXint);
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  Mdir="IDX";
                  /////////////////////////////////////////////// 
                 }
                 
                }
                  }//termina Hexadecimal
                  //Entra Octal
                   if(Operando.matches("^\\@[-]*[0-7]*.*")){
                       IDXcad =IDX.nextToken();
                       int val=IDXcad.length();
                   String IDXcade=IDXcad.substring(1,val);
                  // IDXcad =IDX.nextToken();
                
              boolean IDXB=false;    
             // IDXfirst=IDX.nextToken();
              
              if(Operando.matches("^[aAbBdD],[XxYyspSPpcPC]*")){
                  Mdir="IDX";//Acumulador
                  IDXB=true;
                  
              }
             
              if(Operando.matches("^\\@.*")&&IDXB==false){
                  banRel=true;
                  int  IDXint=Integer.parseInt(IDXcade,8);
                  //contienen Decimales
                  
                   // IDX 5Bits
                 if(IDXint>=-16&&IDXint<=15&&IDXB==false&&Operando.matches(".*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false){
                     IDXB=true;
                     Mdir="IDX";
                     //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir= Integer.toBinaryString(IDXint);
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  Mdir="IDX";
                  /////////////////////////////////////////////// 
                 }
                 //IDX 9 Bits
                 if(IDXint>=-256&&IDXint<=-17&&Operando.matches(".*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false||IDXint>=16&&IDXint<=255&&Operando.matches(".*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false){
                     Mdir="IDX1";
                     IDXB=true;
                     //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir= Integer.toBinaryString(IDXint);
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  /////////////////////////////////////////////// 
                 }
                 //IDX 16Bits
                 if(IDXint<=-257&&IDXint>=-32768&&IDXB==false&&Operando.matches("^.*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false||IDXint>=256&&IDXint<=65535&&IDXB==false&&Operando.matches("^.*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false){
                     Mdir="IDX2";
                     IDXB=true;
                     //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir= Integer.toBinaryString(IDXint);
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////
                 }
                 if(IDXint<=-1&&IDXint>=-8&&Operando.matches(".*,([+|-])*([X|x|Y|y|sp|SP])*[+|-]*$")&&IDXB==false||IDXint<=8&&IDXint>=1&&Operando.matches(".*,([+|-])*([X|x|Y|y|sp|SP])*[+|-]*$")&&IDXB==false){
                     //System.out.println("Operando IDXPPP: "+Operando);
                     Mdir="IDXPP";
                     //System.out.println("Mdir Pre/Post: "+Mdir);
                     //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir= Integer.toBinaryString(IDXint);
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  Mdir="IDX";
                  /////////////////////////////////////////////// 
                 }
                 
                }
              }//termina octal
                   //Entra Binario
                if(Operando.matches("^\\%.*")){
                   
                    IDXcad =IDX.nextToken();
                     
                       int val=IDXcad.length();
                      // System.out.println("Operando: "+Operando+"Cadena IDX "+IDXcad+"Val: "+val);
                 String  IDXcade=IDXcad.substring(1,val);
               //  System.out.println("Operando2: "+IDXcade);
                  // IDXcad =IDX.nextToken();
                  
            boolean IDXB=false;    
             // IDXfirst=IDX.nextToken();
              
              if(Operando.matches("^[aAbBdD],[XxYyspSPpcPC]*")){
                  Mdir="IDXA";//Acumulador
                  IDXB=true;
              }
             
              if(Operando.matches("^\\%.*")&&IDXB==false){
                  banRel=true;
               //   System.out.println("Operando: "+Operando+"Cadena IDX "+IDXcade);
                  int  IDXint=Integer.parseInt(IDXcade,2);
                 // System.out.println("IDXint Binario"+IDXint);
                  
                   // IDX 5Bits
                 if(IDXint>=-16&&IDXint<=15&&IDXB==false&&Operando.matches("^.*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false){
                     IDXB=true;
                     Mdir="IDX";
                     //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir= Integer.toBinaryString(IDXint);
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  Mdir="IDX";
                  /////////////////////////////////////////////// 
                 }
                 //System.out.println("Logico"+IDXB);
                 //IDX 9 Bits
                 if(IDXint>=-256&&IDXint<=-17&&Operando.matches("^.*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false||IDXint>=16&&IDXint<=255&&Operando.matches("^.*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false){
                     Mdir="IDX1";
                     IDXB=true;
                     //System.out.println("Entro binario IDX1");
                  //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir= Integer.toBinaryString(IDXint);
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  /////////////////////////////////////////////// 
                 }
                 //IDX 16Bits
                 if(IDXint<=-257&&IDXint>=-32768&&IDXB==false&&Operando.matches("^.*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false||IDXint>=256&&IDXint<=65535&&IDXB==false&&Operando.matches("^.*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false){
                     Mdir="IDX2";
                     IDXB=true;
                     //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir= Integer.toBinaryString(IDXint);
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////
                 }
                 if(IDXint<=-1&&IDXint>=-8&&Operando.matches(".*,([+|-])*([X|x|Y|y|sp|SP])*[+|-]*$")&&IDXB==false||IDXint<=8&&IDXint>=1&&Operando.matches(".*,([+|-])*([X|x|Y|y|sp|SP])*[+|-]*$")&&IDXB==false){
                     //System.out.println("Operando IDXPPP: "+Operando);
                     Mdir="IDXPP";
                     //System.out.println("Mdir Pre/Post: "+Mdir);
                     //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir= Integer.toBinaryString(IDXint);
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  Mdir="IDX";
                  /////////////////////////////////////////////// 
                 }
                 
                }
              }//termina Binario   
              ////////////////////////////////////////////////////Sin base//////////////////////////////////////////////////////////////////////////////
              }if(Operando.matches("^[-]*([0-9a-dA-D])*,.*")){
                  
                String   IDXcade=IDX.nextToken();
                //   System.out.println("Operando: "+Operando+"Cadena IDX "+IDXcade);
            boolean IDXB=false;    
             // IDXfirst=IDX.nextToken();
              //Indizado de Acumulador
              if(Operando.matches("^[aAbBdD],[XxYyspSPpcPC]*")){
                  
                  Mdir="IDXA";//Acumulador
                  IDXB=true;
                  //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir=IDXcade;//Acumulador
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  Mdir="IDX";
                  /////////////////////////////////////////////// 
              }
             
              if(IDXcade.matches("^[-]?[0-9].*")&&IDXB==false){
                  banRel=true;
                  int  IDXint=Integer.parseInt(IDXcade);
                  //contienen Decimales
                  
                   // IDX 5Bits
                 if(IDXint>=-16&&IDXint<=15&&IDXB==false&&Operando.matches("^[-]?[0-9]*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false){
                     IDXB=true;
                     Mdir="IDX5";
                     
                  //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir= Integer.toBinaryString(IDXint);
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  Mdir="IDX";
                  /////////////////////////////////////////////// 
                 }
                 //IDX 9 Bits
                 if(IDXint>=-256&&IDXint<=-17&&Operando.matches("^[-]?[0-9]*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false||IDXint>=16&&IDXint<=255&&Operando.matches("^[-]?[0-9]*,([X|x|Y|y|sp|SP|pc|PC])*$")&&IDXB==false){
                     Mdir="IDX1";
                     IDXB=true;
                     
                  //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir= Integer.toBinaryString(IDXint);
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  /////////////////////////////////////////////// 
                 }
                 //IDX 16Bits
                 if(IDXint<=-257&&IDXint>=-32768&&IDXB==false&&Operando.matches("^[-]?[0-9]*,([X|x|Y|y|sp|SP|pc|PC])*$")||IDXint>=256&&IDXint<=65535&&IDXB==false&&Operando.matches("^[-]?[0-9]*,([X|x|Y|y|sp|SP|pc|PC])*$")){
                     Mdir="IDX2";
                     IDXB=true;
                     //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir= Integer.toBinaryString(IDXint);
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////
                 }
                 //IDX Pre/Post
                // System.out.println("Operando IDXPPPAfU: "+Operando);
                 if(IDXint<=-1&&IDXint>=-8&&Operando.matches("^[-]*[0-9]*,([+|-])*([X|x|Y|y|sp|SP])*[+|-]*$")&&IDXB==false||IDXint<=8&&IDXint>=1&&Operando.matches("^[-]*[0-9]*,([+|-])*([X|x|Y|y|sp|SP])*[+|-]*$")&&IDXB==false){
                    // System.out.println("Operando IDXPPP: "+Operando);
                     Mdir="IDXPP";
                     //System.out.println("Mdir Pre/Post: "+Mdir);
                     //////////////////// Busqueda de Bytes y CodMaq
                  String op=IDX.nextToken();
                  String MaqDir= Integer.toBinaryString(IDXint);
                  Busqueda =Bytes(codop,Mdir,MaqDir,op);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  Mdir="IDX";
                  /////////////////////////////////////////////// 
                 }
                 
                }else{///////////////////////////// IDX 5Bits sin digitos
                      if(Operando.matches(",([X|x|Y|y|sp|SP|pc|PC])*$")){
                          Mdir="IDX5";
                 //  String   IDXcade=IDX.nextToken();
                   System.out.println("IDXcade: "+IDXcade);
                  //////////////////// Busqueda de Bytes y CodMaq
                 // String op=IDX.nextToken();
                  String MaqDir="0000";
                  Busqueda =Bytes(codop,Mdir,MaqDir,IDXcade);
                  ////////////////////////Operacion para ContLoc
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  Mdir="IDX";
                  /////////////////////////////////////////////// 
                      }else{
                  error.write("Linea: "+lin+" Error el Operando no cumple los requerimientos para Indexados contiene: "+Operando);
                  error.newLine();
              }
              }
             }//termina sin base
          }
          }//termina IDX
          
          /////////////////////////////////////////////////////////////16 Bits Indirecto [IDX2]
          if(Operando.matches("^\\[[\\%\\$\\@]{0,1}[-0-9]+,[XxYyspSPpcPC]*\\]$")){
              //int Idx2=0;
              int tam=Operando.length();
              String IDX2=Operando.substring(1,tam-1);//-1 quita el corchete final
              StringTokenizer  IDX= new StringTokenizer(IDX2,",");
              //System.out.println("Operando[]: "+Operando);
             // System.out.println("[IDX2] "+IDX2);
              if(IDX2.matches("^\\$[0-9A-Fa-f]*.*")||IDX2.matches("^\\@[-]*[0-7]*.*")||IDX2.matches("^\\%.*")){
                  
                  //Entra Hexadecimal
                  if(IDX2.matches("^\\$[0-9A-Fa-f]*.*")){
                      String Aux=IDX.nextToken();
                    int tam2=Aux.length();
                    String hexIdx=Aux.substring(1,tam2);
                    int hex=Integer.parseInt(hexIdx,16);
                    if(hex<=65535&&-32768<=hex){
                        Mdir="[IDX2]";
                        if(hex>=-32768&&hex<=-1){
                       x = hex;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectohex(z);
                       
                      }
                        
                    }
                    
                  }//Termina Hexadecimal
                    //entra Octal
                  if(IDX2.matches("^\\@[-]*[0-7]*.*")){
                      String Aux=IDX.nextToken();
                      int tam2=Aux.length();
                    String octIdx=Aux.substring(1,tam2);
                    int oct=Integer.parseInt(octIdx,8);
                    if(oct<=65535&&-32768<=oct){
                        Mdir="[IDX2]";
                        if(oct>=-32768&&oct<=-1){
                       x = oct;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectooct(z);
                       
                      }
                        
                    }
                  }//Termina Octal
                  //Inicia Binario
                  if(IDX2.matches("^\\%.*")){
                      String Aux=IDX.nextToken();
                       int tam2=Aux.length();
                    String binIdx=Aux.substring(1,tam2);
                    int bin=Integer.parseInt(binIdx,2);
                    if(bin<=65535&&-32768<=bin){
                        Mdir="[IDX2]";
                        if(bin>=-32768&&bin<=-1){
                       x = bin;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectooct(z);
                       
                      }
                        
                    }
                  }//Termina Binario
              }else{//sin base
          if(Operando.matches("^\\[[-0-9]+,[XxYyspSPpcPC]*\\]$")){
                   String Aux=IDX.nextToken();
                       int tam2=Aux.length();
                       //System.out.println("binIdx "+Aux+" "+tam);
                    String binIdx=Aux.substring(0,tam2);
                    int bin=Integer.parseInt(binIdx);
                    if(bin<=65535&&-32768<=bin){
                        Mdir="[IDX2]";
                        
                    }
          }else{
              error.write("Linea: "+lin+" Error el Operando no cumple los requerimientos para [IDX2] contiene: "+Operando);
              error.newLine();
          }
              
          }
          }
          ////////////////////////////////////////////////////////////Indexado Indirecto de Acumulador
          if(Operando.matches("^\\[([D|d]){1,1},")){
              
              
          if(Operando.matches("\\[([D|d])*,([X|x|Y|y|sp|SP|pc|PC])*\\]")){
              Mdir="[D,IDX]";
              
          }else{
              error.write("Linea: "+lin+" Error el Operando no cumple los requerimientos para [D,IDX] contiene: "+Operando);
              error.newLine();
          }
          }
          
          ////////////////////////////////////////////////////////////Inmediato IMM8, IMM16
          if(Operando.matches("^[#]{1,}.*")&&BanContLoc!=1){
              
              int IMM=0;
              int tam=Operando.length();
              String immcad=Operando.substring(1,tam);
              //con base
             // System.out.println("Entro A imm"+Operando);
              if(immcad.matches("^\\@[-]*[0-7]+")||immcad.matches("^\\%[10]*")||immcad.matches("^\\$[0-9A-Fa-f]*"))
                {
               //empieza octal
               if(immcad.matches("^\\@.*")){     
               immcad=Operando.substring(2,tam);
               IMM =Integer.parseInt(immcad, 8);
              
              if(IMM<=255&&-256<=IMM){
                  //System.out.println("Entro A imm8");
                  if(moddir.equals("INM")){
                  Mdir="IMM8";
                  //////////////////// Busqueda de Bytes y CodMaq
                  String et="null";
                  String MaqDir= Integer.toHexString(IMM);
                  MaqDir=fillContLoc(MaqDir);
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////  
                  //return Mdir;
                  if(IMM>=-256&&IMM<=-1){
                       x = IMM;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectooct(z);
                      }
                  }
              }
              else if(IMM<=65535&&-32768<=IMM){
                  //System.out.println("Entro A imm16");
                  if(moddir.equals("INM")){
                  Mdir="IMM16";
                  //////////////////// Busqueda de Bytes y CodMaq
                  String et="null";
                  String MaqDir= Integer.toHexString(IMM);
                  MaqDir=fillContLoc(MaqDir);//rellena los
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////  
                  if(IMM>=-32768&&IMM<=-1){
                       x = IMM;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectooct(z);
                      }
                  }
              }
                }//termina octal
               //entra binario
              if(immcad.matches("^\\%.*")){     
               immcad=Operando.substring(2,tam);
               IMM =Integer.parseInt(immcad, 2);
              
              if(IMM<=255&&-256<=IMM){
                  //System.out.println("Entro A imm8");
                  if(moddir.equals("INM")){
                  Mdir="IMM8";
                  //////////////////// Busqueda de Bytes y CodMaq
                  String et="null";
                  String MaqDir= Integer.toHexString(IMM);
                  MaqDir=fillContLoc(MaqDir);
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////
                  if(IMM>=-256&&IMM<=-1){
                       x = IMM;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectobin(z);
                      }
                  }
              }
              else if(IMM<=65535&&-32768<=IMM){
                  //System.out.println("Entro A imm16");
                  if(moddir.equals("INM")){
                  Mdir="IMM16";
                  //////////////////// Busqueda de Bytes y CodMaq
                  String et="null";
                  String MaqDir= Integer.toHexString(IMM);
                  MaqDir=fillContLoc(MaqDir);//rellena los
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////
                  if(IMM>=-32768&&IMM<=-1){
                       x = IMM;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectobin(z);
                      }
                  }
              }
                }//termina binario
              //entra hexadecimal 
              if(immcad.matches("^\\$.*")){     
               immcad=Operando.substring(2,tam);
               IMM =Integer.parseInt(immcad, 16);//transforma de hexa a decimal para evaluar 
              //System.out.println("Hexadecimal"+IMM);
              if(IMM<=255&&-256<=IMM){
                  //System.out.println("Entro A imm8");
                  if(moddir.equals("INM")){
                  Mdir="IMM8";
                  //////////////////// Busqueda de Bytes y CodMaq
                  String et="null";
                  String MaqDir= Integer.toHexString(IMM);
                  MaqDir=fillContLoc(MaqDir);
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////
                  if(IMM>=-256&&IMM<=-1&&!immcad.matches("^0.*")){
                       x = IMM;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectohex(z);
                      }
                  }
              }
              else if(IMM<=65535&&-32768<=IMM){
                  //System.out.println("Entro A imm16");
                  if(moddir.equals("INM")){
                  Mdir="IMM16";
                  //////////////////// Busqueda de Bytes y CodMaq
                  String et="null";
                  String MaqDir= Integer.toHexString(IMM);
                  MaqDir=fillContLoc(MaqDir);//rellena los
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////
                  if(IMM>=-32768&&IMM<=-1||immcad.matches("^0.*")){
                       x = IMM;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectohex(z);
                      }
                  }
              }
                }//termina hexadecimal
          }else{//sin base 
                  
                  
                  if(immcad.matches("^[0-9].*")){
                  IMM =Integer.parseInt(immcad);
                  System.out.println("Entro A imm"+IMM);
                  if(IMM<=255&&-256<=IMM){
                  //System.out.println("Entro A imm8");
                      if(moddir.equals("INM")){
                  Mdir="IMM8";
                  //////////////////// Busqueda de Bytes y CodMaq
                  String et="null";
                  String MaqDir= Integer.toHexString(IMM);
                  MaqDir=fillContLoc(MaqDir);
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////
                  
                      }
                  }
                  else if(IMM<=65535&&-32768<=IMM){
                   System.out.println("Entro A imm16"+IMM+"moddir "+moddir);
                      if(moddir.equals("INM")){
                  Mdir="IMM16";
                  
                  //////////////////// Busqueda de Bytes y CodMaq
                  String et="null";
                  String MaqDir= Integer.toHexString(IMM);
                  MaqDir=fillContLoc(MaqDir);//rellena los
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  MaqBan=true;
                  ///////////////////////////////////////////////
                  
                      }
                  }
              }else{
                      error.write("Linea: "+lin+" Error "+codop+" no cumple los requerimientos contiene: "+Operando);
                                 error.newLine();
                  }
              }
          }//termina Inmediato
          
          /////////////////////////////////////////////////////////////////////Relativo REL8 & REL16
          if(Operando.matches("^[0-9a-zA-Z].*")&&Operando.matches(".*[^,].*")&&banRel==false&&BanContLoc!=1&&!codop.equals("ORG")||Operando.matches("^\\@[0-7]+")&&Operando.matches(".*[^,].*")&&banRel==false&&BanContLoc!=1&&!codop.equals("ORG")||Operando.matches("^\\%[10]*$")&&Operando.matches(".*[^,].*")&&banRel==false&&BanContLoc!=1&&!codop.equals("ORG")||Operando.matches("^\\$[0-9A-Fa-f]*")&&Operando.matches(".*[^,].*")&&banRel==false&&BanContLoc!=1&&!codop.equals("ORG")){
              int REL=0;
              int tam=Operando.length();
              if(!Operando.matches("^[\\@\\$\\%]*[-]*([0-9a-dA-D])*,[+|-]*([X|x|Y|y|sp|SP|pc|PC])*[+|-]*$")){
             System.out.println("moddir: "+moddir+" Codop: "+codop+" Operando: "+Operando);
              if(Operando.matches("^\\$[0-9A-Fa-f]*")||Operando.matches("^\\@[0-7]+")||Operando.matches("^\\%[10]*$"))
                {
                    //Entra Octal
                    if(Operando.matches("^\\@.*")){
              String relcad=Operando.substring(1,tam);
              REL =Integer.parseInt(relcad, 8);
              if(REL<=255&&REL>=-256){
                  if(codop.matches("^[lL].*")){
                  if(moddir.equals("REL")){
                      Mdir="REL16";
                      
                      if(REL>=-256&&REL<=-1){
                       x = REL;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectooct(z);
                      }
                  }
                  }else{
                    if(moddir.equals("REL")){
                      Mdir="REL8";
                      if(REL>=-256&&REL<=-1){
                       x = REL;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectooct(z);
                      }
                   }
                  }
              }
              else if(codop.matches("^[lL].*")||REL<=65535&&REL>=-32768){
                   if(moddir.equals("REL")){
                      Mdir="REL16";
                     ;
                      if(REL>=-32768&&REL<=-1){
                       x = REL;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectooct(z);
                      }
                  }
              }
                }//Termina Octal
                    
                  //Empieza Binario
                 if(Operando.matches("^\\%.*")){
              String relcad=Operando.substring(1,tam);
              REL =Integer.parseInt(relcad, 2);
              if(REL<=255&&REL>=-256){
                  if(codop.matches("^[lL].*")){
                  if(moddir.equals("REL")){
                      Mdir="REL16";
                      if(REL>=-256&&REL<=-1){
                       x = REL;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectobin(z);
                      }
                  }
                  }else{
                    if(moddir.equals("REL")){
                      Mdir="REL8";
                      if(REL>=-256&&REL<=-1){
                       x = REL;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectobin(z);
                      }
                   }
                  }
              }
              else if(codop.matches("^[lL].*")||REL<=65535&&REL>=-32768){
                   if(moddir.equals("REL")){
                      Mdir="REL16";
                      if(REL>=-32768&&REL<=-1){
                       x = REL;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectobin(z);
                      }
                  }
              }
                }//Termina Binario
                 //Empieza Hexadecimal
                 if(Operando.matches("^\\$.*")){
              String relcad=Operando.substring(1,tam);
              REL =Integer.parseInt(relcad, 16);
              if(REL<=255&&REL>=-256){
                  if(codop.matches("^[lL].*")){
                  if(moddir.equals("REL")){
                      Mdir="REL16";
                      if(REL>=-256&&REL<=-1||relcad.matches("^0.*")){
                       x = REL;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectohex(z);
                      }
                  }
                  }else{
                    if(moddir.equals("REL")){
                      Mdir="REL8";
                      if(REL>=-256&&REL<=-1||relcad.matches("^0.*")){
                       x = REL;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectohex(z);
                      }
                   }
                  }
              }
              else if(codop.matches("^[lL].*")&&REL<=65535&&REL>=-32768){
                   if(moddir.equals("REL")){
                      Mdir="REL16";
                      if(REL>=-32768&&REL<=-1||relcad.matches("^0.*")){
                       x = REL;  
                       y = ~x;   
                       z = y + 1;
                       Res=dectohex(z);
                       
                      }
                  }
              }
                }//Termina Hexadecimal
                }else{//sin base
                  if(Operando.matches("^[0-9].*")){
                  REL =Integer.parseInt(Operando, 10);
                  if(REL<=255&&REL>=-256){
                    if(codop.matches("^[lL].*")){
                  if(moddir.equals("REL")){
                      Mdir="REL16";
                      
                  }
                  }else{
                        if(moddir.equals("REL")){
                      Mdir="REL8";
                       
                        }
                  }
              }
              else if(codop.matches("^[lL].*")||REL<=65535&&REL>=-32768){
                   if(moddir.equals("REL")){
                      Mdir="REL16";
                      
                  }
              }
                  
              }else{
                     if(Operando.matches("^[a-zA-Z][a-zA-Z]*$")&&codop.matches("^[lL].*")){
                         if(moddir.equals("REL")){
                      Mdir="REL16";
                      
                          }
                         }
                     else{
                         if(moddir.equals("REL")){
                      Mdir="REL8";
                          }
                     }
                  }
              }
              }
          }//Termina REL
                        
          if(Mdir=="null"&&BanContLoc!=1&&!codop.equals("ORG")){
          error.write("Linea: "+lin+" Error no se encontro ningun modo de direccionamiento");
          error.newLine();
          }
         /*
          if(Mdir!="null"){
              Byte=Bytes(codop,Mdir);
              }*/
      } /////////////////////Termina validacion de Operando
       else{
          
          error.write("Linea: "+lin+" Error el modo de Direccionamiento no es valido");
          error.newLine();
          
      }
       
          error.close();
          if(Mdir!="null"&&BanContLoc==0&&MaqBan==false){
                  ///Calcular bytes
                  String MaqDir=" ",et="null";
                  Busqueda =Bytes(codop,Mdir,MaqDir,et);
                  String cadby=Busqueda[0];
                  int byt=Integer.parseInt(cadby);
                  int cont=Integer.parseInt(ContLoc,16);
                  cont=cont+byt;
                  ContLoc=Integer.toHexString(cont).toUpperCase();
                  ///////////////////////////////////////////////
          }
        }catch(Exception e){
            System.out.println("Hubo un problema en los modos de direccionamiento: "+e);
            
        }
        ContLoc=fillContLoc(ContLoc);
        ContLoc2=fillContLoc(ContLoc2);
        //System.out.println("Mdir: "+Mdir+"Res op: "+Res);
        Resultado[0]=Mdir;
        Resultado[1]=Res;
        Resultado[2]=Integer.toString(BanOrg);
        Resultado[3]=ContLoc;
        Resultado[4]=Busqueda[1].toUpperCase();//Codigo maquina
        Resultado[5]=FCC3;
        Resultado[6]=ContLoc2;
       System.out.println(" Mdir: "+Resultado[0]+" Res op: "+Resultado[1]+" Ban ORG: "+Resultado[2]+" ContLoc: "+Resultado[3]);
      return Resultado;
    }///////////////////////////////////Termina Direccion
    
    public static int hextodec(String hexdecnum)
    {
             int decimal = Integer.parseInt(hexdecnum, 16);
             return decimal;
    }
    
    public static String dectohex(int dec)
    {
        String hex = Integer.toHexString(dec);
        return hex;
    }
    public String dectobin(int dec){
        
        
        String bin = Integer.toBinaryString(dec);
        
        return bin;
    }
    public String dectooct(int dec){
        
        String oct = Integer.toOctalString(dec);
        return oct;
    }
    public String[] Bytes(String codop, String dir,String CodMaq,String EtOp){
                   String[] Bytes=new String[]{"null","null"};
        
                       String TABOP="TABOP";
                         String mayus,exCod,IDX5="null",Operando="null",IMM="null";
                          dir.toUpperCase();
                          if(dir.equals("IMM8")||dir.equals("IMM16")){
                              dir="INM";
                          }
                          if(dir.equals("REL8")||dir.equals("REL16")){
                              dir="REL";
                          }
                          if(dir.equals("IDX5")){
                              IDX5=dir;
                              dir="IDX";
                              Operando=EtOp;
                              EtOp="null";
                          }
                          if(dir.equals("IDXPP")){
                              IDX5=dir;
                              dir="IDX";
                              Operando=EtOp;
                              EtOp="null";
                          }
                          if(dir.equals("IDXA")){
                              IDX5=dir;
                              dir="IDX";
                              Operando=EtOp;
                              EtOp="null";
                          }
                         try{
                             FileInputStream fsaux = new FileInputStream(TABOP+".asm");
                             DataInputStream dsaux = new DataInputStream(fsaux);
                             BufferedReader  braux = new BufferedReader(new InputStreamReader(dsaux));
                             
                             String linaux;
                             //System.out.println("Tab lin "+linToken);
                             
                             while((linaux = braux.readLine())!= null){
                                 
                                 StringTokenizer aucod = new StringTokenizer(linaux,"|");
                                        mayus=codop;
                                   exCod=aucod.nextToken();
                                 //  System.out.println("Tabop: "+exCod);
                                   
                                   if(exCod.compareTo(mayus.toUpperCase())==0&&mayus!="null"&&mayus!=null&&mayus!=" "){
                                      
                                       
                                       if(codop!="null"&&codop!=null&&codop!=" "){
                                           
                                       
                                   //     System.out.println("Codop comparado "+codop);
                                       
                                       
                                     //  System.out.println("Auxiliar "+linaux);
                                     /*String moddir=aucod.nextToken("|");   //Modo de direccionamiento  
                                      String codcal=aucod.nextToken("|");  //Codigo maquina calculado
                                     String totbytes=aucod.nextToken("|"); //Total de bytes
                                     String  bytescal=aucod.nextToken("|"); //Bytes calculados
                                     String  bytesxcal=aucod.nextToken("|");  //Bytes por calcular  
                                     */
                                      String   sioperS=aucod.nextToken("|");    //Vrifica si lleva operando
                                      int  sioperI=Integer.parseInt(sioperS); //convierte de String a Cadena
                                     String  moddir=aucod.nextToken("|");   //Modo de direccionamiento  
                                     String  codcal=aucod.nextToken("|");  //Codigo Maquina calculado
                                    String   bytescal=aucod.nextToken("|"); //Bytes calculados
                                     String  bytesxcal=aucod.nextToken("|");  //Bytes por calcular
                                     String  totbytes=aucod.nextToken("|");  //Total de bytes
                            /*           System.out.print("Codop: "+codop);
                                       System.out.print(" Modo de direccionamiento: "+moddir);
                                       System.out.print(" Codigo calculado: "+codcal);
                                       System.out.print(" Bytes calculados: "+bytescal);
                                       System.out.print(" Bytes por calcular: "+bytesxcal);
                                       System.out.println(" Total de bytes: "+totbytes);
                              */       
                                       if(dir.equals(moddir)){
                                           Bytes[0]=bytesxcal;
                                       }
                                       if(dir.equals(moddir)&&dir.equals("DIR")){
                                           Bytes[0]=bytesxcal;
                                           Bytes[1]=codcal+CodMaq;
                                          // System.out.println("Bytesxcal: "+Bytes[0]+" CodMaq: "+Bytes[1]);
                                       }
                                       if(dir.equals(moddir)&&dir.equals("EXT")&&EtOp=="null"){
                                           Bytes[0]=bytesxcal;
                                           Bytes[1]=codcal+CodMaq;
                                           //System.out.println("Bytesxcal: "+Bytes[0]+" CodMaq: "+Bytes[1]);
                                       }
                                       if(dir.equals(moddir)&&dir.equals("INM")){
                                           Bytes[0]=bytesxcal;
                                           Bytes[1]=codcal+CodMaq;
                                          // System.out.println("Bytesxcal: "+Bytes[0]+" CodMaq: "+Bytes[1]);
                                       }
                                       if(dir.equals(moddir)&&dir.equals("REL")){
                                           Bytes[0]=bytesxcal;
                                       }
                                       if(dir.equals(moddir)&&dir.equals("EXT")&&EtOp!="null"){
                                           
                                           Bytes[1]=codcal+CodMaq; 
                                       }
                                       if(dir.equals(moddir)&&IDX5.equals("IDX5")&&Operando!="null"){
                                           int siz=CodMaq.length();
                                           //System.out.println("Binario: "+CodMaq);
                                           if(siz>4){
                                               String auxbin=CodMaq.substring(0, siz-5);
                                               int siz2=auxbin.length();
                                               auxbin=CodMaq.substring(siz2,siz);
                                               CodMaq="0"+auxbin;
                                               System.out.println("Binario2: "+CodMaq);
                                           }else{
                                               if(siz==1){
                                                  CodMaq="00000"+CodMaq;
                                               }else{
                                               CodMaq="00"+CodMaq;
                                               }
                                           }
                                           String rr=Operando;
                                           rr=rr.toUpperCase();
                                           if(rr.equals("X")){
			                    rr="00";
		                            }
		                            if(rr.equals("Y")){
			                     rr="01";
		                            }
		                             if(rr.equals("SP")){
			                     rr="10";
		                            }
		                             if(rr.equals("PC")){
			                      rr="11";
		                            }
                                             System.out.println("Binario2: "+CodMaq+" Operando "+rr);
                                            String rr0nnnnn=rr+CodMaq;
                                             String rr0n=rr0nnnnn.substring(0,8-4);
                                            // fillContLoc(rr0n);
                                             int a=Integer.parseInt(rr0n, 2);
                                             String nnnn=rr0nnnnn.substring(4,8);
                                             int b=Integer.parseInt(nnnn, 2);
                                             //int c=Integer.parseInt(rr0nnnnn, 2);
                                             String hex=Integer.toHexString(a);
                                             String hex2=Integer.toHexString(b);
                                             //String hex3=Integer.toHexString(c);
                                             Bytes[0]=bytesxcal;
                                             Bytes[1]=codcal+hex.toUpperCase()+hex2.toUpperCase();
                                             System.out.println("CodMaq: "+Bytes[1]+"codcal: "+codcal+" rr0nnnnn: "+rr0nnnnn+" rr0n: "+rr0n+"nnnn: "+nnnn+"Int1: "+a+" Int2: "+b+" rr: "+rr+" CodMaq: "+CodMaq+" Binario2: "+hex+" Binario3: "+hex2+" Binario3: ");
                                            }//Termina IDX
                                       if(dir.equals(moddir)&&dir.equals("IDX1")&&EtOp!="null"){
                                          String z="0",s="0",n111="111";
                                           int siz=CodMaq.length();
                                           int valor=Integer.parseInt(CodMaq,2);
                                           String rr=EtOp,VHex2="null";
                                           rr=rr.toUpperCase();
                                           if(rr.equals("X")){
			                    rr="00";
		                            }
		                            if(rr.equals("Y")){
			                     rr="01";
		                            }
		                             if(rr.equals("SP")){
			                     rr="10";
		                            }
		                             if(rr.equals("PC")){
			                      rr="11";
		                            }
                                           //  System.out.println("Valor IDX1: "+valor);
                                             if(-256<=valor&&valor<0){
                                                 s="1";
                                                 String VHex=Integer.toHexString(valor);
                                                  VHex2=VHex.substring(14,16);
                                                 
                                             }
                                             else{
                                                  VHex2=Integer.toHexString(valor);
                                             }
                                             System.out.println("ValorHex: "+VHex2);
                                             String R111rr0zs=n111+rr+"0"+z+s;
                                             String R111r=R111rr0zs.substring(0,7-3);
                                             int a=Integer.parseInt(R111r,2);
                                             String r0zs=R111rr0zs.substring(4,8);
                                             int b=Integer.parseInt(r0zs,2);
                                             String hex=Integer.toHexString(a);
                                             String hex2=Integer.toHexString(b);
                                             // System.out.println("R111rr0zs "+R111rr0zs+" R111r: "+R111r+" r0zs: "+r0zs+" n111: "+n111+" rr: "+rr+" z: "+z+" s: "+s);
                                             Bytes[0]=bytesxcal;
                                             Bytes[1]=codcal+hex.toUpperCase()+hex2.toUpperCase()+VHex2;
                                       }//Termina IDX1
                                       if(dir.equals(moddir)&&dir.equals("IDX2")&&EtOp!="null"){
                                       String z="1",s="0",n111="111";
                                           int siz=CodMaq.length();
                                           int valor=Integer.parseInt(CodMaq,2);
                                           String rr=EtOp,VHex2="null";
                                           rr=rr.toUpperCase();
                                           if(rr.equals("X")){
			                    rr="00";
		                            }
		                            if(rr.equals("Y")){
			                     rr="01";
		                            }
		                             if(rr.equals("SP")){
			                     rr="10";
		                            }
		                             if(rr.equals("PC")){
			                      rr="11";
		                            }
                                             System.out.println("Valor IDX2: "+valor);
                                             if(-32768<=valor&&valor<0){
                                                 s="1";
                                                 String VHex=Integer.toHexString(valor);
                                                  VHex2=VHex.substring(12,16);
                                                 
                                             }
                                             else{
                                                  VHex2=Integer.toHexString(valor);
                                             }
                                             System.out.println("ValorHex: "+VHex2);
                                             String R111rr0zs=n111+rr+"0"+z+s;
                                             String R111r=R111rr0zs.substring(0,8-4);
                                             int a=Integer.parseInt(R111r,2);
                                             String r0zs=R111rr0zs.substring(4,8);
                                             int b=Integer.parseInt(r0zs,2);
                                             String hex=Integer.toHexString(a);
                                             String hex2=Integer.toHexString(b);
                                            // System.out.println("111rr0zs "+R111rr0zs+" R111r: "+R111r+" r0zs: "+r0zs+" n111: "+n111+" rr: "+rr+" z: "+z+" s: "+s);
                                             Bytes[0]=bytesxcal;
                                             Bytes[1]=codcal+hex.toUpperCase()+hex2.toUpperCase()+VHex2;
                                       }//Termina IDX2  
                                       if(dir.equals(moddir)&&IDX5.equals("IDXPP")&&Operando!="null"){
                                           int siz=Operando.length();
                                           String rr="00",p="0",nnnn="0000";
                                           int valor;
                                           
                                           if(Operando.matches("^[+][XxYySPspPCpc]{1,2}$")||Operando.matches("^[XxYySPspPCpc]{1,2}[+]$")){
                                               p="1";
                                               if(Operando.matches("^[+][XxYySPspPCpc]{1,2}$")){
                                               
                                               rr=Operando.substring(1,siz);
                                               
                                               }
                                               if(Operando.matches("^[XxYySPspPCpc]{1,2}[+]$")){
                                               rr=Operando.substring(0,siz-1);
                                               }
                                               valor=Integer.parseInt(CodMaq,2);
                                               valor--;
                                               if(valor==1){
                                                   nnnn="0000";
                                               }else
                                               {
                                                   nnnn=Integer.toBinaryString(valor);
                                               }
                                             //  System.out.println("CodMaq: "+CodMaq+" valor: "+valor);
                                           }
                                           if(Operando.matches("^[-][XxYySPspPCpc]{1,2}$")||Operando.matches("^[XxYySPspPCpc]{1,2}[-]$")){
                                               p="0";
                                               if(Operando.matches("^[-][XxYySPspPCpc]{1,2}$")){
                                                   
                                               rr=Operando.substring(1,siz);
                                               
                                               }
                                               if(Operando.matches("^[XxYySPspPCpc]{1,2}[-]$")){
                                               rr=Operando.substring(0,siz-1);
                                               }
                                               
                                               //valor=Integer.parseInt(CodMaq,2);
                                                  // String Aux=Integer.toBinaryString(valor);
                                                   int n=CodMaq.length();
                                                   
                                                  nnnn=CodMaq.substring(0,n-4);
                                                  int n2=nnnn.length();
                                                  //System.out.println("CodMaq: "+CodMaq+" n: "+n+" n2: "+n2);
                                                  //System.out.println("Operando: "+Operando+" rr: "+rr+" siz: "+siz);
                                                  //System.out.println("rr: "+rr+"n: "+n+"n2: "+n2);
                                                  nnnn=CodMaq.substring(n2,n);
                                                  //System.out.println("nnnn: "+nnnn);
                                           }
                                           rr=rr.toUpperCase();
                                           if(rr.equals("X")){
			                    rr="00";
		                            }
		                            if(rr.equals("Y")){
			                     rr="01";
		                            }
		                             if(rr.equals("SP")){
			                     rr="10";
		                            }
		                             if(rr.equals("PC")){
			                      rr="11";
		                            }
                                             String rr1pnnnn=rr+"1"+p+nnnn;
                                             String rr1p=rr1pnnnn.substring(0,4);
                                             int a=Integer.parseInt(rr1p,2);
                                             int b=Integer.parseInt(nnnn,2);
                                             String hex=Integer.toHexString(a);
                                             String hex2=Integer.toHexString(b);
                                             Bytes[0]=bytesxcal;
                                             Bytes[1]=codcal+hex+hex2;
                                             System.out.println("CodMaq: "+Bytes[1]+" rr1pnnnn: "+rr1pnnnn+" rr1p: "+rr1p+" nnnn: "+nnnn);
                                       }//Termina IDX Pre Post
                                       if(dir.equals(moddir)&&IDX5.equals("IDXA")&&Operando!="null"){
                                          String X111rr1aa="0",aa="0",rr="0";
                                          aa=CodMaq;
                                          rr=Operando;
                                          aa=aa.toUpperCase();
                                          rr=rr.toUpperCase();
                                           if(rr.equals("X")){
			                    rr="00";
		                            }
		                            if(rr.equals("Y")){
			                     rr="01";
		                            }
		                             if(rr.equals("SP")){
			                     rr="10";
		                            }
		                             if(rr.equals("PC")){
			                      rr="11";
		                            }
                                            if(aa.equals("A")){
                                                aa="00";
                                            }
                                            if(aa.equals("B")){
                                                aa="01";
                                            }
                                            if(aa.equals("D")){
                                                aa="10";
                                            }
                                          X111rr1aa="111"+rr+1+aa;
                                          String X111r=X111rr1aa.substring(0,8-4);
                                          String r1aa=X111rr1aa.substring(4,8);
                                          int a=Integer.parseInt(X111r,2);
                                          int b=Integer.parseInt(r1aa,2);
                                          String hex=Integer.toHexString(a);
                                          String hex2=Integer.toHexString(b);
                                          Bytes[0]=bytesxcal;
                                          Bytes[1]=codcal+hex+hex2;
                                          System.out.println("CodMaq: "+Bytes[1]+" X111rr1aa: "+X111rr1aa+" X111r: "+X111r+" r1aa: "+r1aa);
                                       }//Termina IDX Acumulador
                                       
                                       
                                       }
                                   }
                                   
                                   }
                             dsaux.close(); 
                          }catch(Exception r){
                             System.out.println("Hubo un error en la busqueda de Bytes "+r);
                         }
        
        
        return Bytes;
    }
    public int TabsimCheck(String dir, String Etq){
        
        
                         int compara=0;
                         String mayus,exEtq;
                          
                         try{
                             FileInputStream fsaux = new FileInputStream(dir+".tds");
                             DataInputStream dsaux = new DataInputStream(fsaux);
                             BufferedReader  braux = new BufferedReader(new InputStreamReader(dsaux));
                             
                             String linaux;
                            
                             
                             while((linaux = braux.readLine())!= null){
                                 
                                 StringTokenizer aucod = new StringTokenizer(linaux,"|");
                                        mayus=Etq;
                                   exEtq=aucod.nextToken();
                                 
                                   
                                   if(exEtq.toUpperCase().compareTo(mayus.toUpperCase())==0&&mayus!="null"&&mayus!=null&&mayus!=" "){
              
                                       compara=1;
                                   }
                                   
                                   }
                             dsaux.close(); 
                          }catch(Exception r){
                             System.out.println("Hubo un error en la busqueda de Tabsim "+r);
                         }
        
        
        return compara;
    }
    public String TabsimCheck2(String dir, String Oper){
        
        
                         
                         String mayus,exOper,ContLoc=null;
                          
                         try{
                             FileInputStream fsaux = new FileInputStream(dir+".tds");
                             DataInputStream dsaux = new DataInputStream(fsaux);
                             BufferedReader  braux = new BufferedReader(new InputStreamReader(dsaux));
                             
                             String linaux;
                            
                             
                             while((linaux = braux.readLine())!= null){
                                 
                                 StringTokenizer aucod = new StringTokenizer(linaux,"|");
                                        mayus=Oper;
                                   exOper=aucod.nextToken();
                                 
                                   System.out.println("Operando: "+Oper+" Opertds: "+exOper);
                                   if(exOper.toUpperCase().compareTo(mayus.toUpperCase())==0/*&&mayus!="null"&&mayus!=null&&mayus!=" "*/){
                                     ContLoc =aucod.nextToken("|");
                                       System.out.println("ContLoc ins: "+ContLoc);
                                       
                                   }
                                   
                                   }
                             dsaux.close(); 
                          }catch(Exception r){
                             System.out.println("Hubo un error en la busqueda de Tabsim 2: "+r);
                         }
        
        
        return ContLoc;
    }
    
    public String fillContLoc(String ContLoc){
        
        int size =ContLoc.length();
        switch(size){
            
            case 1: 
                ContLoc="000"+ContLoc;
                break;
            case 2:
                ContLoc="00"+ContLoc;
                break;
            case 3:
                ContLoc="0"+ContLoc;
            case 4:
                break;
        }
          return ContLoc;      
        }
        public String[] fillline(String Lin,String ContLoc,String etiqueta,String codop,String operando,String Mdir){
        String[] Linea = new String[]{" "," "," "," "," "," "};
        int size =Lin.length();
        int size2 =ContLoc.length();
        int size3 =etiqueta.length();
        int size4 =codop.length();
        int size5 =operando.length();
        int size6 =Mdir.length();
        
        switch(size){
            
            case 1: 
                Linea[0]="000"+Lin;
                break;
            case 2:
                Linea[0]="00"+Lin;
                break;
            case 3:
                Linea[0]="0"+Lin;
            case 4:
                break;
        }
         switch(size2){
            
            case 1: 
                Linea[1]="000"+ContLoc;
                break;
            case 2:
                Linea[1]="00"+ContLoc;
                break;
            case 3:
                Linea[1]="0"+ContLoc;
            case 4:
                Linea[1]=ContLoc;
                break;
        }
        
        switch(size3){
            
            case 1: 
                Linea[2]=etiqueta+"       ";
                break;
            case 2:
                Linea[2]=etiqueta+"      ";
                break;
            case 3:
                Linea[2]=etiqueta+"     ";
                break;
            case 4:
                Linea[2]=etiqueta+"    ";
                break;
            case 5:
                Linea[2]=etiqueta+"   ";
                break;
            case 6:
                Linea[2]=etiqueta+"  ";
                break;
            case 7:
                Linea[2]=etiqueta+" ";
                break;
            case 8:
                Linea[2]=etiqueta;
                break;    
        }
        switch(size4){
            
            case 1: 
                Linea[3]=codop+"    ";
                break;
            case 2:
                Linea[3]=codop+"   ";
                break;
            case 3:
                Linea[3]=codop+"  ";
                break;
            case 4:
                Linea[3]=codop+" ";
                break;
            case 5:
                Linea[3]=codop;
                break;  
        }
        switch(size5){
            
            case 1: 
                Linea[4]=operando+"             ";
                break;
            case 2:
                Linea[4]=operando+"            ";
                break;
            case 3:
                Linea[4]=operando+"           ";
                break;
            case 4:
                Linea[4]=operando+"          ";
                break;
            case 5:
                Linea[4]=operando+"         ";
                break;
            case 6:
                Linea[4]=operando+"        ";
                break;
            case 7:
                Linea[4]=operando+"       ";
                break;
            case 8:
                Linea[4]=operando+"      ";
                break;    
            case 9:
                Linea[4]=operando+"     ";
                break;
            case 10:
                Linea[4]=operando+"    ";
                break;
                case 11:
                Linea[4]=operando+"   ";
                break;
                case 12:
                Linea[4]=operando+"  ";
                break;
                case 13:
                Linea[4]=operando+" ";
                break;
                case 14:
                Linea[4]=operando;
                default:
                Linea[4]=operando;    
                break;
        }
        switch(size6){
            
            case 1: 
                Linea[5]=Mdir+"       ";
                break;
            case 2:
                Linea[5]=Mdir+"      ";
                break;
            case 3:
                Linea[5]=Mdir+"     ";
                break;
            case 4:
                Linea[5]=Mdir+"    ";
                break;
            case 5:
                Linea[5]=Mdir+"   ";
                break;
            case 6:
                Linea[5]=Mdir+"  ";
                break;
            case 7:
                Linea[5]=Mdir+" ";
                break;
            case 8:
                Linea[5]=Mdir;
                break;    
        }
          return Linea;      
        }
        public void Ordena(String dir,String etiqueta){
            
            String mayus,exetq,ContLoc=null;
                          
                         try{
                             FileInputStream fsaux = new FileInputStream(dir+".INST");
                             DataInputStream dsaux = new DataInputStream(fsaux);
                             BufferedReader  braux = new BufferedReader(new InputStreamReader(dsaux));
                             
                             String linaux,ContLocAux="null",numaux="null";
                            String[] auxiliar =new String[]{"null","null","null","null","null","null","null"};
                            String[] Resultado =new String[]{"null","null"};
                             boolean contban=false;
                             while((linaux = braux.readLine())!= null){
                                 
                                 StringTokenizer aucod = new StringTokenizer(linaux,"       ");
                                        mayus=etiqueta;
                                   auxiliar[0]=aucod.nextToken();//Linea
                                   auxiliar[1]=aucod.nextToken();//ContLoc
                                   auxiliar[2]=aucod.nextToken();//Etiqueta
                                   auxiliar[3]=aucod.nextToken();//Codop
                                   auxiliar[4]=aucod.nextToken();//Oper
                                   auxiliar[5]=aucod.nextToken();//Modo de direccion
                                   auxiliar[6]=aucod.nextToken();//Codigo Maquina
                                   System.out.println("Operando: "+etiqueta+" Opertds: "+auxiliar[2]);
                                   if(!auxiliar[2].equals(mayus)){
                                       File fsaux2 = new File(dir+"2"+".INST");
                                       FileWriter dsaux2 = new FileWriter(fsaux2);
                                       BufferedWriter  instruccion2 = new BufferedWriter(dsaux2);
                                           String linaux2;   
                                               //ordena el archivo instruccion 
                                             instruccion2.write(auxiliar[0]+"       "+auxiliar[1]+"        "+auxiliar[2]+"        "+auxiliar[3]+"        "+auxiliar[4]+"       "+auxiliar[5]+"     "+auxiliar[6]);                 
                                              ContLocAux=auxiliar[1];
                                              numaux=auxiliar[0];
                                       instruccion2.close();
                                   }else{
                                       File fsaux2 = new File(dir+"2"+".INST");
                                       FileWriter dsaux2 = new FileWriter(fsaux2);
                                       BufferedWriter  instruccion2 = new BufferedWriter(dsaux2);
                                           String linaux2;   
                                               //ordena el archivo instruccion 
                                       if(ContLocAux!="null"){                      
                                  instruccion2.write(numaux+"       "+ContLocAux+"        "+auxiliar[2]+"        "+auxiliar[3]+"        "+auxiliar[4]+"       "+auxiliar[5]+"     "+auxiliar[6]);                 
                                       }else{     
                                      String dum="null",dum2="null";
                                      
                                      Resultado=Bytes(auxiliar[3],auxiliar[5],dum,dum2);
                                      auxiliar[1]=Resultado[0];
                                      instruccion2.write(auxiliar[0]+"       "+auxiliar[1]+"        "+auxiliar[2]+"        "+auxiliar[3]+"        "+auxiliar[4]+"       "+auxiliar[5]+"     "+auxiliar[6]);
                                       
                                       }    
                                       instruccion2.close();
                                       contban=true;
                                   }
                                   ContLocAux="null";
                                   numaux="null";
                                   }
                             dsaux.close(); 
                          }catch(Exception r){
                             System.out.println("Hubo un error en el metodo Ordena "+r);
                         }
        
        
       
            
        }//Termina Ordena
    }
    
