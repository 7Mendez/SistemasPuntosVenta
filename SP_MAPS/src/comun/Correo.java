package comun;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

public class Correo
{
    protected String para;
    protected String de;
    protected String deTitulo;
    protected String cc;
    protected String cco;
    protected String asunto;
    protected String mensaje;
    protected String nombreUsuario;
    protected String contrasena;
    protected String host;

    public Correo()
    {
      limpiar();
    }

    public void setPara(String xTexto) {para = xTexto;}
    public void setDe(String xTexto) {de = xTexto;}
    public void setDeTitulo(String xTexto) {deTitulo = xTexto;}
    public void setCC(String xTexto) {cc = xTexto;}
    public void setCco(String xTexto) {cco = xTexto;}
    public void setAsunto(String xTexto) {asunto = xTexto;}
    public void setMensaje(String xTexto) {mensaje = xTexto;}
    public void setNombreUsuario(String xTexto) {nombreUsuario = xTexto;}
    public void setContrasena(String xTexto) {contrasena = xTexto;}
    public void setHost(String xTexto) {host = xTexto;}
    public void setCredenciales(String xHost, String xUsuario, String xContrasena)
    {
      host = xHost;
      nombreUsuario=xUsuario;
      contrasena=xContrasena;
      de=xUsuario;
    }

    public void limpiar()
    {
      para="";
      de="";
      deTitulo="";
      cc="";
      cco="";
      asunto="";
      mensaje="";
      nombreUsuario="";
      contrasena="";
      host="";
    }

    public void enviar(String tipo)
    {
      asunto=Utilerias.acentosHTML(asunto);
      mensaje=Utilerias.acentosHTML(mensaje);
      try{

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");

        Session session2 = Session.getDefaultInstance(props, null);
        MimeMessage message2 = new MimeMessage(session2);
        message2.setFrom(new InternetAddress(de,deTitulo));
        message2.addRecipient(Message.RecipientType.TO,
                              new InternetAddress(para));
        if (!cc.equals("")) {message2.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));}
        if (!cco.equals("")) {message2.addRecipient(Message.RecipientType.BCC, new InternetAddress(cco));}

        message2.setSubject(asunto);
        if (tipo=="html"){message2.setContent(mensaje.toString(), "text/html");}else{message2.setText(mensaje.toString());}
        message2.saveChanges(); // implicit with send()
        Transport transport = session2.getTransport("smtp");
        transport.connect(host, nombreUsuario, contrasena);
        transport.sendMessage(message2, message2.getAllRecipients());
        transport.close();

      } catch(Exception ex)
      {
        System.out.println(ex.getMessage());
      }
    }
}