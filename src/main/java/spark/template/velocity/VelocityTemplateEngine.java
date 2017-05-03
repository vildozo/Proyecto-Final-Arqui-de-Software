package spark.template.velocity;

import static spark.Spark.get;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import spark.Request;
import spark.Response;
//import spark.template.velocity.VelocityEngine;

/**
 * VelocityTemplateRoute example.
 */
public final class VelocityTemplateEngine {
	VelocityEngine ve = new VelocityEngine();
	VelocityContext context = new VelocityContext();
	Template t;
	StringWriter writer = new StringWriter();
	
	public VelocityTemplateEngine(){
		ve.init();	
		
	}
/*	
    public static void main(final String[] args) {
    	//get and initialize engine
    			//VelocityEngine ve = new VelocityEngine();
    	
    			ve.init();
    			
    			//next, get the template
    			Template t = ve.getTemplate("src/main/java/Hello.vt");
    			
    			//Create a context and add data
    			VelocityContext context = new VelocityContext();
    			context.put("first", "Lesly");
    			context.put("born_in", "Bolivia");
    			
    			//now render the template into a stringwriter
    			StringWriter writer = new StringWriter();
    			t.merge(context, writer);
    			
    			//show the world
    			System.out.println( writer.toString());
    }
    */
	
	public void asignarDireccionTemplate(String direccion){
    	this.t=ve.getTemplate(direccion);
    }
	
    public void agregarContext(String nombre_Variable, String valor){
    	this.context.put(nombre_Variable, valor);
    }  
    
    public  void unirAlTemplate(){
    	t.merge(context, writer);
    }
    public String show_template(){
    	return ( writer.toString());
    }
    
    public String construir_y_visualizar(){
    	unirAlTemplate();
    	return show_template();
    	
    }
    
}