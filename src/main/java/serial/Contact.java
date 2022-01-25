package serial;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;
    private final String name;

    public Contact(String name, int zipCode, String phone) {
        this.name = name;
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + ", name='" + name + '\'' + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact("Inokentiy Smoktunovskiy", 123456, "8-800-5553535");

        File tempFile = Files.createTempFile(null, null).toFile();

        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }

        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            final Contact contactFromTempFile = (Contact) ois.readObject();
            System.out.println(contactFromTempFile);
        }
    }
}
