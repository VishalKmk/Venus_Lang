import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class VirtualMachine {
    private final String[] stack;
    private int stackPointer;

    public VirtualMachine() {
        stack = new String[1000];
        stackPointer = 0;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: VirtualMachine <program.vmc>");
            System.exit(1);
        }

        try {
            String programFile = args[0];
            if (!programFile.endsWith(".vmc")) {
                programFile += ".vmc";
            }

            Path path = Path.of(programFile);
            if (!Files.exists(path)) {
                System.err.println("Error: File not found - " + programFile);
                System.exit(1);
            }

            String bytecode = Files.readString(path);
            VirtualMachine vm = new VirtualMachine();
            vm.execute(bytecode);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
    }

    private void execute(String bytecode) {
        String[] instructions = bytecode.split("\n");
        for (String instruction : instructions) {
            instruction = instruction.trim();
            if (instruction.isEmpty() || instruction.startsWith("#")) {
                continue;
            }

            String[] parts = instruction.split(" ", 2);
            String opcode = parts[0];

            switch (opcode) {
                case "PUSH":
                    if (parts.length > 1) {
                        String value = parts[1].trim();
                        if (value.startsWith("\"") && value.endsWith("\"")) {
                            value = value.substring(1, value.length() - 1);
                        }
                        stack[stackPointer++] = value;
                    }
                    break;
                case "PRINT":
                    if (stackPointer > 0) {
                        System.out.println(stack[--stackPointer]);
                    }
                    break;
            }
        }
    }
}