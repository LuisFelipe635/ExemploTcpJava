/**
 * @author Tarcisio da Rocha (Prof. DCOMP/UFS)
 */
package br.ufs.dcomp.ExemploTcpJava;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPServer{
    public static void main(String[] args){
        
        try {
            System.out.print("[ Iniciando Servidor TCP    .........................  ");
            ServerSocket ss = new ServerSocket(3300, 5, InetAddress.getByName("127.0.0.1"));
            System.out.println("[OK] ]");
            
            System.out.print("[ Aquardando pedidos de conexão    ..................  ");
            Socket sock = ss.accept(); // Operação bloqueante (aguardando pedido de conexão)
            System.out.println("[OK] ]");
            
            InputStream is = sock.getInputStream(); //Canal de entrada de dados
            OutputStream os = sock.getOutputStream(); //Canal de saída de dados
            
            String msg3;
            String msg4;

            while (true) {
                byte[] buf = new byte[200]; // buffer de recepção
                System.out.print("[ Aguardando mensagem   ..............  ");
                is.read(buf); // Operação bloqueante (aguardando chegada de dados)
                System.out.println("[OK] ]");
            
                msg3 = new String(buf); // Mapeando vetor de bytes recebido para String
            
                System.out.println("  Cliente: "+ msg3);
                buf = null;
                if (msg3.equals("Desligue"))
                    break;
                
                Scanner sc = new Scanner(System.in);
                msg4 = sc.nextLine();
                byte[] buf2 = msg4.getBytes();
                System.out.print("[ Enviando mensagem    ..............................  ");
                os.write(buf2);
                System.out.println("[OK] ]");
                buf2 = null;
                if (msg4.equals("Desligue"))
                    break;
            } 
        }
        
        catch(Exception e){
            System.out.println(e);
        }    
        System.out.println("[ FIM ]");
    }
}