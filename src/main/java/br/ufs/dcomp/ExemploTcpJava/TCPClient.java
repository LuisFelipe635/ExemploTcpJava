/**
 * @author Tarcisio da Rocha (Prof. DCOMP/UFS)
 */
package br.ufs.dcomp.ExemploTcpJava;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient{
    public static void main(String[] args){
        try {
            System.out.print("[ Conectando com o Servidor TCP    ..................  ");
            Socket sock = new Socket("127.0.0.1", 3300);
            System.out.println("[OK] ]");
            
            InputStream is = sock.getInputStream(); // Canal de entrada de dados
            OutputStream os = sock.getOutputStream(); // Canal de saída de dados
            
            String msg;
            String msg2;
            
            while(true) {
                Scanner sc = new Scanner(System.in);
                msg = sc.nextLine();
                byte[] buf = msg.getBytes(); // Obtendo a respresntação em bytes da mensagem

                System.out.print("[ Enviando mensagem    ..............................  ");
                os.write(buf);
                System.out.println("[OK] ]");
                buf = null;
                if (msg.equals("Desligue"))
                    break;
            
                byte[] buf2 = new byte[200];
                System.out.print("[ Aguardando mensagem   ..............  ");
                is.read(buf2); // Operação bloqueante (aguardando chegada de dados)
                System.out.println("[OK] ]");
            
                msg2 = new String(buf2); // Mapeando vetor de bytes recebido para String
            
                System.out.println("  Servidor: "+ msg2);
                buf2 = null;
                if (msg2.equals("Desligue"))
                    break;
            } 
        }
        
        catch(Exception e){
            System.out.println(e);
        }    
        System.out.println("[ FIM ]");
    }
}