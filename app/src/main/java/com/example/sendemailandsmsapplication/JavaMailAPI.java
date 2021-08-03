package com.example.sendemailandsmsapplication;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailAPI extends AsyncTask<Void,Void,Void> {
    private Context context;
    private Session session;
    private String fname,lname,email,subject,message,contact,password;

    public JavaMailAPI(Context context, String fname,String lname, String email, String contact,String password) {
        this.context = context;
        //this.session = session;
        this.fname=fname;
        this.lname=lname;
        this.email = email;
        // this.subject = subject;
        //  this.message = message;
        this.contact=contact;
        this.password=password;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Properties proerties = new Properties();
        proerties.put("mail.smtp.host", "smtp.gmail.com");
        proerties.put("mail.smtp.socketFactory.port", "465");
        proerties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        proerties.put("mail.smtp.auth", "true");
        proerties.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(proerties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Utils.Email,Utils.Password);
            }
        });
        MimeMessage mimeMessage=new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(Utils.Email));
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));
            mimeMessage.setText(contact);
            mimeMessage.setText(fname);
            mimeMessage.setText(lname);
            mimeMessage.setText(password);
            // mimeMessage.setText(message);
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
