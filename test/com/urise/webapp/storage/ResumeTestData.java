package com.urise.webapp.storage;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.ListSection;
import com.urise.webapp.model.Organization;
import com.urise.webapp.model.OrganizationSection;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.TextSection;

import java.time.Month;

public class ResumeTestData {

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    static final Resume RESUME_1;
    static final Resume RESUME_2;
    static final Resume RESUME_3;
    static final Resume RESUME_4;

    static {

        RESUME_1 = new Resume(UUID_1, "John Snow");
        RESUME_2 = new Resume(UUID_2, "Bob Marley");
        RESUME_3 = new Resume(UUID_3, "Harry Potter");
        RESUME_4 = new Resume(UUID_4, "Dunno on the Moon");

        RESUME_1.addContact(ContactType.MOBILE, "+79194411301");
        RESUME_1.addContact(ContactType.MAIL, "john@gmail.com");
        RESUME_1.addContact(ContactType.LINKEDIN, "johnLinkedin.com");
        RESUME_1.addContact(ContactType.GITHUB, "johngit@github.com");
        RESUME_1.addContact(ContactType.TELEGRAM, "GotTelega");
        RESUME_1.addSection(SectionType.PERSONAL, new TextSection("Personal info about candidate"));
        RESUME_1.addSection(SectionType.OBJECTIVE, new TextSection("Java position"));
        RESUME_1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achievement 1", "Achievement 2"));
        RESUME_1.addSection(SectionType.QUALIFICATIONS, new ListSection("java", "java spring"));
        RESUME_1.addSection(SectionType.EXPERIENCE, new OrganizationSection
                (new Organization("IBS", "ibs.ru", new Organization.Position(2014, Month.APRIL, 2015, Month.AUGUST, "Java Junior", "")),
                        new Organization("Tinkoff", "tink.ru", new Organization.Position(2015, Month.AUGUST, 2019, Month.MARCH, "Java Senior", ""))));
        RESUME_1.addSection(SectionType.EDUCATION, new OrganizationSection(new Organization("school 22", null, new Organization.Position(2000, Month.APRIL, 2011, Month.AUGUST, "schoolboy", ""))));

        RESUME_2.addContact(ContactType.MOBILE, "+456546");
        RESUME_2.addContact(ContactType.MAIL, "bob@gmail.com");
        RESUME_2.addContact(ContactType.TELEGRAM, "bobm");
        RESUME_2.addSection(SectionType.PERSONAL, new TextSection("Personal info about candidate"));
        RESUME_2.addSection(SectionType.OBJECTIVE, new TextSection("QA position"));
        RESUME_2.addSection(SectionType.ACHIEVEMENT, new ListSection("Achievement 1", "Achievement 2"));
        RESUME_2.addSection(SectionType.QUALIFICATIONS, new ListSection("java", "java spring", "QA"));
        RESUME_2.addSection(SectionType.EXPERIENCE, new OrganizationSection
                (new Organization("IBS", "ibs.ru", new Organization.Position(2014, Month.APRIL, 2015, Month.AUGUST, "Java Junior", "")),
                        new Organization("Tinkoff", "tink.ru", new Organization.Position(2015, Month.AUGUST, 2019, Month.MARCH, "Java Senior", ""))));
        RESUME_2.addSection(SectionType.EDUCATION, new OrganizationSection(new Organization("school 22", null, new Organization.Position(2000, Month.APRIL, 2011, Month.AUGUST, "schoolboy", ""))));

        RESUME_3.addContact(ContactType.MOBILE, "+435345");
        RESUME_3.addContact(ContactType.MAIL, "bob@gmail.com");
        RESUME_3.addContact(ContactType.TELEGRAM, "Harry");
        RESUME_3.addSection(SectionType.PERSONAL, new TextSection("Personal info about candidate"));
        RESUME_3.addSection(SectionType.OBJECTIVE, new TextSection("Intern"));
        RESUME_3.addSection(SectionType.QUALIFICATIONS, new ListSection("java", "java spring", "QA"));
        RESUME_3.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("school 22", null, new Organization.Position(2000, Month.APRIL, 2011, Month.AUGUST, "schoolboy", "")),
                new Organization("MGU", "mgu.ru", new Organization.Position(2011, Month.SEPTEMBER, 2016, Month.AUGUST, "student", ""))
        ));

        RESUME_4.addContact(ContactType.MAIL, "Dunno@gmail.com");
        RESUME_4.addContact(ContactType.TELEGRAM, "test");
    }

}
