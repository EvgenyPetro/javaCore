package ru.geekbrains.lesson2.HM3;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class Main {

    public static final String FILE_JSON = "student.json";
    public static final String FILE_BIN = "student.bin";
    public static final String FILE_XML = "student.xml";

    private static ObjectMapper mapper = new ObjectMapper();
    private static XmlMapper xmlMapper = new XmlMapper();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student("Evgeniy", 36, 87);

        serializeJSON(student);
        serializeXML(student);
        serializeBIN(student);

        System.out.println(deserializeBIN());
        System.out.println(deserializeJSON());
        System.out.println(deserializeXML());

    }

    public static void serializeJSON(Student student) throws IOException {
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.writeValue(new File(FILE_JSON), student);

    }

    public static void serializeXML(Student student) throws IOException {
        xmlMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        xmlMapper.writeValue(new File(FILE_XML), student);
    }

    public static void serializeBIN(Student student) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(FILE_BIN));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(student);
        }
    }

    public static Student deserializeBIN() throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(new File(FILE_BIN));
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (Student) objectInputStream.readObject();
        }
    }
    public static Student deserializeJSON() throws IOException, ClassNotFoundException {
        return mapper.readValue(new File(FILE_JSON), Student.class);
    }
    public static Student deserializeXML() throws IOException, ClassNotFoundException {
        return xmlMapper.readValue(new File(FILE_XML), Student.class);
    }


}
