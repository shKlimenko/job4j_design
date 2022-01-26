package serial;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class JAXBVesta {
    public static void main(String[] args) throws JAXBException, IOException {
        CarModel car = new CarModel(
                true, 4, "Lada", new Dashboard(17, false), new String[] {"white", "blue"});

        JAXBContext context = JAXBContext.newInstance(CarModel.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            CarModel result = (CarModel) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
