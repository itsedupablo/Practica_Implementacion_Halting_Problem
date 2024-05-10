package haltingProblem.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import haltingProblem.controller.haltCheckHandlers.bucles.*;
import haltingProblem.controller.syntaxAnalysis.SyntaxAnalysisHandler;
import haltingProblem.controller.haltCheckHandlers.comparadores.*;
import haltingProblem.controller.haltCheckHandlers.incrementoDecremento.*;
import haltingProblem.controller.haltCheckHandlers.definicionVariables.*;
import haltingProblem.model.*;
import haltingProblem.controller.*;

public class App {
    private static JFrame frame;
    private static JTextArea textArea;
    private static JButton reverseButton;

    static Handler handlerRoot = new SyntaxAnalysisHandler();
    static WhileLoopHandler whileLoopHandler = new WhileLoopHandler();
    static Handler definicionBooleanosHandler = new DefinicionBooleanosHandler();
    static Handler definicionEnterosHandler = new DefinicionEnterosHandler();
    static Handler definicionFlotantesHandler = new DefinicionFlotantesHandler();
    static IncrementoDirectoHandler incrementoDirectoHandler = new IncrementoDirectoHandler();
    static Handler incrementoValorAsignadoHandler = new IncrementoValorAsignadoHandler();
    static Handler decrementoDirectoHandler = new DecrementoDirectoHandler();
    static Handler decrementoValorAsignadoHandler = new DecrementoValorAsignadoHandler();
    static Handler comparadorIgualHandler = new ComparadorIgualHandler();
    static Handler comparadorDesigualHandler = new ComparadorDesigualHandler();
    static Handler comparadorMayorIgualHandler = new ComparadorMayorIgualHandler();
    static ComparadorMayorHandler comparadorMayorHandler = new ComparadorMayorHandler();
    static Handler comparadorMenorIgualHandler = new ComparadorMenorIgualHandler();
    static Handler comparadorMenorHandler = new ComparadorMenorHandler();

    static HaltChecker haltChecker = new HaltChecker(whileLoopHandler, incrementoDirectoHandler, comparadorMayorHandler);
    static Reverser reverser = new Reverser(haltChecker);

    public static void main(String[] args) {
        frame = new JFrame("Verificador de archivos");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel opcionesPanel = new JPanel();
        opcionesPanel.setLayout(new GridLayout(3, 1));

        JButton contarUpButton = new JButton("CountUp");
        JButton contarDownButton = new JButton("CountDown");
        reverseButton = new JButton("Reverse");
        reverseButton.setEnabled(false); // Desactivar el botón "Reverse" al inicio

        contarUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verificarArchivo("resources/CountUp.txt");
            }
        });

        contarDownButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verificarArchivo("resources/CountDown.txt");
            }
        });

        reverseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Invertir el resultado del HaltChecker
                boolean reversedResult = Reverser.reverse();
                if (reversedResult) {
                    textArea.append("Reversed --> El programa no tiene un bucle infinito y por tanto para.\n");
                } else {
                    textArea.append("Reversed --> El programa tiene un bucle infinito, y por tanto nunca para.\n");
                }
            }
        });

        opcionesPanel.add(new JLabel("Seleccione un archivo para verificar:"));
        opcionesPanel.add(contarUpButton);
        opcionesPanel.add(contarDownButton);
        opcionesPanel.add(reverseButton); // Agregar el nuevo botón

        textArea = new JTextArea();
        textArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.add(opcionesPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);
    }

    private static void verificarArchivo(String archivo) {
        try {
            String codigoFuente = SyntaxAnalysisHandler.leerTexto(archivo);

            handlerRoot.setNext(whileLoopHandler);
            whileLoopHandler.setNext(definicionBooleanosHandler);
            definicionBooleanosHandler.setNext(definicionFlotantesHandler);
            definicionFlotantesHandler.setNext(definicionEnterosHandler);
            definicionEnterosHandler.setNext(incrementoValorAsignadoHandler);
            incrementoValorAsignadoHandler.setNext(incrementoDirectoHandler);
            incrementoDirectoHandler.setNext(decrementoValorAsignadoHandler);
            decrementoValorAsignadoHandler.setNext(decrementoDirectoHandler);
            decrementoDirectoHandler.setNext(comparadorIgualHandler);
            comparadorIgualHandler.setNext(comparadorDesigualHandler);
            comparadorDesigualHandler.setNext(comparadorMayorHandler);
            comparadorMayorHandler.setNext(comparadorMayorIgualHandler);
            comparadorMayorIgualHandler.setNext(comparadorMenorHandler);
            comparadorMenorHandler.setNext(comparadorMenorIgualHandler);
            comparadorMenorIgualHandler.setNext(null);

            handlerRoot.handle(codigoFuente);

            boolean sePara = haltChecker.haltCheck();

            if (sePara) {
                textArea.append("El programa no tiene un bucle infinito, y por tanto para.\n");
                reverseButton.setEnabled(true); // Activar el botón "Reverse"
            } else {
                textArea.append("El programa tiene un bucle infinito y por tanto nunca para.\n");
                reverseButton.setEnabled(true); // Activar el botón "Reverse"
            }

        } catch (Exception e) {
            e.printStackTrace(); // Imprimir la pila de llamadas
            textArea.append("Error: " + e.getMessage() + "\n");
        }
    }
}

class CustomOutputStream extends OutputStream {
    private JTextArea textArea;

    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        textArea.append(String.valueOf((char) b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
