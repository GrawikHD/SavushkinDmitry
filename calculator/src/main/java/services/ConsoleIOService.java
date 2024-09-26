package services;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleIOService implements IOService {
    public PrintStream out;
    public Scanner in;

    public ConsoleIOService() {
        this.out = System.out;
        this.in = new Scanner(System.in);
    }

    @Override
    public String readMessage() {
        return in.nextLine();
    }

    @Override
    public void out(String message) {
        out.println(message);
    }
}
