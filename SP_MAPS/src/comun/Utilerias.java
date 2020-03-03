package comun;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

public final class Utilerias {
	public Utilerias() {

	}

	public static void saludo() {
		System.out.println("HOLA");
	}

	public static String encriptarDel(String cadena) {
		String miOriginal, Encriptada, Temporal;

		miOriginal = "ABCDEFGHIJKLMN-�OPQRSTUVWXYZabcdefghijklmn�opqrstuvwxyz1234567890-";
		Encriptada = "KLO8IGH56fJRX1wDxTUzByF�V7v3MN4�SEWQA-ghklmn0CYpqr92PstuZabcdeijo?";

		Temporal = "";

		int tamOriginal = miOriginal.length();
		int tamEncriptada = Encriptada.length();
		int tamCadena = cadena.length();

		int encontrado = 0;
		int miMod = 0;

		for (int i = 0; i < (tamCadena); i++)
		// r i = 0 To tamCadena - 1
		{
			for (int j = 0; j < (tamEncriptada); j++) {
				System.out.println("i=" + i + " j=" + j);
				// r j = 0 To tamEncriptada - 1
				char cadI = cadena.charAt(i);
				char cadO = miOriginal.charAt(j);
				if (cadI == cadO) {
					if (i % 2 == 0) {
						Temporal = Temporal + Encriptada.charAt(j);
					} else {
						if (j == tamEncriptada - 1) {
							Temporal = Temporal + Encriptada.charAt(0);
						} else {
							Temporal = Temporal + Encriptada.charAt(j + 1);
						}
					}
					break;
				}
			}
		}
		return Temporal;
	}

	public static String generaCadena(int cantidad) {
		String original = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
		String temporal = "";
		String contrasena = "";
		for (int x = 0; x < cantidad; x++) {
			int aleatorio = 1 + (int) (Math.random()
					* (double) original.length() - 1);
			char xContrasena = original.charAt(aleatorio);
			contrasena = String.valueOf(contrasena)
					+ String.valueOf(xContrasena);
		}
		return contrasena;
	}

	public static String generaCadenaNumeros(int cantidad) {
		String original = "0123456789";
		String temporal = "";
		String contrasena = "";
		for (int x = 0; x < cantidad; x++) {
			int aleatorio = 1 + (int) (Math.random()
					* (double) original.length() - 1);
			char xContrasena = original.charAt(aleatorio);
			contrasena = String.valueOf(contrasena)
					+ String.valueOf(xContrasena);
		}
		return contrasena;
	}

	public static String generaArchivosNumeros(int cantidad) {
		String original = "123456789";
		String temporal = "";
		String contrasena = "";
		for (int x = 0; x < cantidad; x++) {
			int aleatorio = 1 + (int) (Math.random()
					* (double) original.length() - 1);
			char xContrasena = original.charAt(aleatorio);
			contrasena = String.valueOf(contrasena)
					+ String.valueOf(xContrasena);
		}
		return contrasena;
	}

	public static String generaCadenaLetras(int cantidad) {
		String original = "ABCDEFGHIJKLMNOPWRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String temporal = "";
		String contrasena = "";
		for (int x = 0; x < cantidad; x++) {
			int aleatorio = 1 + (int) (Math.random()
					* (double) original.length() - 1);
			char xContrasena = original.charAt(aleatorio);
			contrasena = String.valueOf(contrasena)
					+ String.valueOf(xContrasena);
		}
		return contrasena;
	}

	public static String reemplazar(String cadena, String busqueda,
			String reemplazo) {
		return cadena.replaceAll(busqueda, reemplazo);
	}

	public static String convertirMinutos(int minutos) {
		String ret = "";
		int dia = minutos / 1440;
		int hor = (minutos - dia * 24 * 60) / 60;
		int min = minutos - dia * 24 * 60 - hor * 60;
		String xdia = String.valueOf(dia);
		String xhor = rellenarCeros(String.valueOf(hor), 2);
		String xmin = rellenarCeros(String.valueOf(min), 2);
		String tiempo = "";
		if (dia > 0)
			tiempo = String.valueOf(String.valueOf((new StringBuffer(String
					.valueOf(String.valueOf(tiempo)))).append(xdia).append("D")
					.append(xhor).append(":").append(xmin)));
		else if (hor > 0)
			tiempo = String.valueOf(String.valueOf((new StringBuffer(String
					.valueOf(String.valueOf(tiempo)))).append(xhor).append(":")
					.append(xmin)));
		else
			tiempo = String.valueOf(String.valueOf((new StringBuffer(String
					.valueOf(String.valueOf(tiempo)))).append("00:").append(
					xmin)));
		ret = tiempo;
		return ret;
	}

	public static float redondeaCalificacion(float cal) {
		float ret = cal;
		String cad = formatoDoble(ret, 3);
		ret = Float.parseFloat(cad.substring(0, cad.length() - 2));
		return ret;
	}

	public static String generaContrasena(int digitos) {
		int bandera = 1;
		String contrasena = "";
		while (bandera == 1) {
			String original = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ABCDEFGHIJKLMN";
			String temporal = "";
			contrasena = "";
			for (int x = 0; x < digitos; x++) {
				int aleatorio = 1 + (int) (Math.random() * (double) 30);
				char xContrasena = original.charAt(aleatorio);
				contrasena = String.valueOf(contrasena)
						+ String.valueOf(xContrasena);
			}
			Resultados rs = UtilDB
					.ejecutaConsulta("Select * From Usuarios Where contrasena='"
							+ contrasena + "'");
			if (rs.recordCount() == 0) {
				bandera = 0;
			}
		}
		return contrasena;
	}

	public static double redondear(double numero, int decimales) {
		double numero_original = numero;
		String parte_entera = String.valueOf(numero_original).substring(0,
				String.valueOf(numero_original).indexOf("."));
		String parte_decimal = String.valueOf(numero_original).substring(
				String.valueOf(numero_original).indexOf(".") + 1);
		if (decimales < 1)
			return (double) Math.round(Double.parseDouble(String
					.valueOf(numero_original)));
		if (parte_decimal.length() <= 1)
			return numero_original;
		int ceros = 0;
		for (int i = 0; i < parte_decimal.length()
				&& parte_decimal.charAt(i) == '0'; i++)
			ceros++;

		parte_decimal = parte_decimal.substring(ceros);
		if (decimales < ceros)
			parte_decimal = "0";
		else if (decimales == ceros) {
			if (Integer.parseInt(String.valueOf(parte_decimal.charAt(0))) >= 5) {
				ceros--;
				parte_decimal = "";
				for (; ceros > 0; ceros--)
					parte_decimal = "0".concat(String.valueOf(String
							.valueOf(parte_decimal)));

				parte_decimal = String.valueOf(String.valueOf(parte_decimal))
						.concat("1");
			} else {
				parte_decimal = "0";
			}
		} else {
			for (; decimales - ceros < parte_decimal.length(); parte_decimal = String
					.valueOf(Math.round(Double.parseDouble(String
							.valueOf(parte_decimal)) / 10D)))
				;
			for (; ceros > 0; ceros--)
				parte_decimal = "0".concat(String.valueOf(String
						.valueOf(parte_decimal)));

		}
		if (String
				.valueOf(numero_original)
				.substring(String.valueOf(numero_original).indexOf(".") + 1,
						String.valueOf(numero_original).indexOf(".") + 2)
				.equals("9")
				&& parte_decimal.substring(0, 1).equals("1"))
			return Double.parseDouble(parte_entera) + 1.0D;
		else
			return Double.parseDouble(String.valueOf(String
					.valueOf((new StringBuffer(String.valueOf(String
							.valueOf(parte_entera)))).append(".").append(
							parte_decimal))));
	}

	public static float redondeaSalario(float cal) {
		float ret = cal;
		String cad = formatoDoble(ret, 3);
		ret = Float.parseFloat(cad.substring(0, cad.length() - 3));
		return ret;
	}

	public static int truncar(float cal) {
		float ret = cal;
		String temp = "";
		String cad = formatoDoble(ret, 3);
		temp = cad.substring(0, cad.length() - 4);
		return Integer.parseInt(temp);
	}

	public static String truncarTxt(float cal, int digitos) {
		float ret = cal;
		String temp = "";
		String cad = formatoDoble(ret, 3);
		if (digitos == 0) {
			temp = cad.substring(0, cad.length() - 3 + digitos - 1);
		} else {
			temp = cad.substring(0, cad.length() - 3 + digitos);
		}
		return temp;
	}

	public static float truncarFloat(float cal, int digitos) {
		float ret = cal;
		String cad = formatoDoble(ret, 3);
		if (digitos == 0) {
			ret = Float.parseFloat(cad.substring(0, cad.length() - 3 + digitos
					- 1));
		} else {
			ret = Float
					.parseFloat(cad.substring(0, cad.length() - 3 + digitos));
		}
		return ret;
	}

	public static float NoredondeaCalificacion(float cal) {
		float ret = cal;
		String cad = formatoDoble(ret, 3);
		ret = Float.parseFloat(cad.substring(0, cad.length() - 2));
		return ret;
	}

	public static String getMes(Calendar cal) {
		return mes[cal.get(2)];
	}


	public static String VacioACero(String cad) {
		return cad != null && !cad.equals("") ? cad.trim() : "0";
	}

	public static String NuloAVacio(String cad) {
		return cad != null ? cad.trim() : "";
	}

	public static String soloNombre(String archivo) {
		archivo = archivo.replace('\\', '@');
		int bandera = 0;
		while (bandera == 0) {
			int loc = archivo.lastIndexOf("@");
			archivo = archivo.substring(loc + 1).toLowerCase();
			loc = archivo.lastIndexOf("@");
			if (loc == -1)
				bandera = 1;
		}
		return archivo;
	}

	public static String CadenaEncomillada(String valor) {
		String cad = valor;
		for (int i = valor.length() - 1; i >= 0; i--) {
			if (!valor.substring(i, i + 1).equals("'"))
				continue;
			if (i == 0)
				cad = "'".concat(String.valueOf(String.valueOf(cad)));
			else
				cad = String.valueOf(String.valueOf((new StringBuffer(String
						.valueOf(String.valueOf(cad.substring(0, i))))).append(
						"'").append(cad.substring(i, cad.length()))));
		}

		return new String(String.valueOf(String.valueOf((new StringBuffer("'"))
				.append(cad).append("'"))));
	}

	public static String CadenaEncomilladaLike(String valor) {
		String cad = valor;
		for (int i = valor.length() - 1; i >= 0; i--) {
			if (!valor.substring(i, i + 1).equals("'"))
				continue;
			if (i == 0)
				cad = "'".concat(String.valueOf(String.valueOf(cad)));
			else
				cad = String.valueOf(String.valueOf((new StringBuffer(String
						.valueOf(String.valueOf(cad.substring(0, i))))).append(
						"'").append(cad.substring(i, cad.length()))));
		}

		return new String(String.valueOf(String
				.valueOf((new StringBuffer("'%")).append(cad).append("%'"))));
	}

	public static String rellenarCeros(String cad, int lng) {
		String pattern = "000000000000000000000000000000000";
		if (cad.equals(""))
			return cad;
		else
			return String.valueOf(String.valueOf(String.valueOf(pattern
					.substring(0, lng - cad.length()))))
					+ String.valueOf(String.valueOf(String.valueOf(cad)));
	}

	public static String rellenarGuiones(String cad, int lng) {
		String pattern = "----------------------------------";
		if (cad.equals("") || lng == 0)
			return cad;
		else
			return String.valueOf(String.valueOf(String.valueOf(pattern
					.substring(0, lng))))
					+ String.valueOf(String.valueOf(String.valueOf(cad)));
	}

	public static Vector split(String pattern, String in) {
		int s1 = 0;
		int s2 = -1;
		Vector out = new Vector(30);
		do {
			s2 = in.indexOf(pattern, s1);
			if (s2 != -1) {
				out.addElement(in.substring(s1, s2));
			} else {
				String _ = in.substring(s1);
				if (_ != null && !_.equals(""))
					out.addElement(_);
				break;
			}
			s1 = s2;
			s1 += pattern.length();
		} while (true);
		return out;
	}

	public static String strAcrobat(String valor) {
		String cad = valor;
		for (int i = valor.length() - 1; i >= 0; i--) {
			if (!valor.substring(i, i + 1).equals("(")
					&& !valor.substring(i, i + 1).equals(")")
					&& !valor.substring(i, i + 1).equals("\\"))
				continue;
			if (i == 0)
				cad = "\\".concat(String.valueOf(String.valueOf(cad)));
			else
				cad = String.valueOf(String.valueOf((new StringBuffer(String
						.valueOf(String.valueOf(cad.substring(0, i))))).append(
						"\\").append(cad.substring(i, cad.length()))));
		}

		return cad;
	}

	public static String formatoCurrency(double valor) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return nf.format(valor);
	}

	public static String formatoDoble(double valor) {
		DecimalFormat nf = new DecimalFormat("###,###,##0.00");
		return nf.format(valor);
	}

	public static String formatoDobleSinComa(double valor) {
		DecimalFormat nf = new DecimalFormat("###,###,##0.00");
		return nf.format(valor);
	}

	public static String formatoDobleEntero(double valor) {
		DecimalFormat nf = new DecimalFormat("###,###,##0");
		return nf.format(valor);
	}

	public static String formatoDoble(double valor, int digitos) {
		String pattern = "0000000000";
		DecimalFormat nf = null;
		if (digitos > 0)
			nf = new DecimalFormat("###,###,##0.".concat(String.valueOf(String
					.valueOf(pattern.substring(0, digitos)))));
		else
			nf = new DecimalFormat("###,###,##0");
		return nf.format(valor);
	}

	public static String formatoLetras(int valor) {
		String letras = "";
		if (valor == 0)
			letras = "Cero";
		if (valor == 1)
			letras = "Uno";
		if (valor == 2)
			letras = "Dos";
		if (valor == 3)
			letras = "Tres";
		if (valor == 4)
			letras = "Cuatro";
		if (valor == 5)
			letras = "Cinco";
		if (valor == 6)
			letras = "Seis";
		if (valor == 7)
			letras = "Siete";
		if (valor == 8)
			letras = "Ocho";
		if (valor == 9)
			letras = "Nueve";
		if (valor == 10)
			letras = "Diez";
		return letras;
	}

	public static String formatoLetras2(int valor) {
		int digito = 0;
		int primerDigito = 0;
		int segundoDigito = 0;
		int tercerDigito = 0;
		int numeroBloques = 0;
		int bloqueCero = 0;
		String bloque = "";
		String cantidadEnLetra = valor != 0 ? "" : "CERO";
		String Unidades[] = { "UNO", "DOS", "TRES", "CUATRO", "CINCO", "SEIS",
				"SIETE", "OCHO", "NUEVE", "DIEZ", "ONCE", "DOCE", "TRECE",
				"CATORCE", "QUINCE", "DIECISEIS", "DIECISIETE", "DIECIOCHO",
				"DIECINUEVE", "VEINTE", "VEINTIUNO", "VEINTIDOS", "VEINTITRES",
				"VEINTICUATRO", "VEINTICINCO", "VEINTISEIS", "VEINTISIETE",
				"VEINTIOCHO", "VEINTINUEVE" };
		String Decenas[] = { "DIEZ", "VEINTE", "TREINTA", "CUARENTA",
				"CINCUENTA", "SESENTA", "SETENTA", "OCHENTA", "NOVENTA" };
		String Centenas[] = { "CIENTO", "DOSCIENTOS", "TRESCIENTOS",
				"CUATROCIENTOS", "QUINIENTOS", "SEISCIENTOS", "SETECIENTOS",
				"OCHOCIENTOS", "NOVECIENTOS" };
		numeroBloques = 1;
		if (valor != 0)
			do {
				primerDigito = 0;
				segundoDigito = 0;
				tercerDigito = 0;
				bloque = "";
				bloqueCero = 0;
				int i = 1;
				do {
					if (i > 3)
						break;
					digito = valor % 10;
					if (digito != 0)
						switch (i) {
						case 1: // '\001'
							bloque = " ".concat(String.valueOf(String
									.valueOf(Unidades[digito - 1])));
							primerDigito = digito;
							break;

						case 2: // '\002'
							if (digito <= 2)
								bloque = " "
										.concat(String.valueOf(String
												.valueOf(Unidades[(digito * 10 + primerDigito) - 1])));
							else
								bloque = String
										.valueOf(String
												.valueOf((new StringBuffer(" "))
														.append(Decenas[digito - 1])
														.append(primerDigito == 0 ? " "
																: " Y")
														.append(bloque)));
							segundoDigito = digito;
							break;

						case 3: // '\003'
							bloque = String
									.valueOf(String
											.valueOf((new StringBuffer(" "))
													.append(digito != 1
															|| primerDigito != 0
															|| segundoDigito != 0 ? Centenas[digito - 1]
															: "CIEN ").append(
															bloque)));
							tercerDigito = digito;
							break;
						}
					else
						bloqueCero++;
					valor = (int) Math.floor(valor / 10);
					if (valor == 0)
						break;
					i++;
				} while (true);
				switch (numeroBloques) {
				case 1: // '\001'
					cantidadEnLetra = bloque;
					break;

				case 2: // '\002'
					cantidadEnLetra = String.valueOf(String
							.valueOf((new StringBuffer(String.valueOf(String
									.valueOf(bloque)))).append(
									bloqueCero != 3 ? " MIL" : " ").append(
									cantidadEnLetra)));
					break;

				case 3: // '\003'
					cantidadEnLetra = String.valueOf(String
							.valueOf((new StringBuffer(String.valueOf(String
									.valueOf(bloque)))).append(
									primerDigito != 1 || segundoDigito != 0
											|| tercerDigito != 0 ? " MILLONES"
											: " MILLON")
									.append(cantidadEnLetra)));
					break;
				}
				numeroBloques++;
			} while (valor != 0);
		return cantidadEnLetra;
	}

	public static String formatoCardinales(int valor) {
		String letras = "";
		if (valor == 1)
			letras = "Primer";
		if (valor == 2)
			letras = "Segundo";
		if (valor == 3)
			letras = "Tercer";
		if (valor == 4)
			letras = "Cuarto";
		if (valor == 5)
			letras = "Quinto";
		if (valor == 6)
			letras = "Sexto";
		if (valor == 7)
			letras = "Septimo";
		if (valor == 8)
			letras = "Octavo";
		if (valor == 9)
			letras = "Noveno";
		if (valor == 10)
			letras = "Decimo";
		return letras;
	}

	public static String formatoCardinalesCortos(int valor) {
		String letras = "";
		if (valor == 1)
			letras = "1er";
		if (valor == 2)
			letras = "2do";
		if (valor == 3)
			letras = "3er";
		if (valor == 4)
			letras = "4to";
		if (valor == 5)
			letras = "5to";
		if (valor == 6)
			letras = "6to";
		if (valor == 7)
			letras = "7mo";
		if (valor == 8)
			letras = "8vo";
		if (valor == 9)
			letras = "9no";
		if (valor == 10)
			letras = "10mo";
		return letras;
	}

	public static String formatoFechaDB(String fecha) {
		String fechaDB = "";
		
		//30-12-2000 23:99
		//0123456789012345
		
		if (fecha.length()==8)
		{
				fechaDB="{ts'" + fecha.substring(6, 10) + '-' + fecha.substring(3, 5) + "-" + fecha.substring(0, 2) + " 00:00:00'}";
		}
		else
		{
				fechaDB="{ts'" + fecha.substring(6, 10) + '-' + fecha.substring(3, 5) + "-" + fecha.substring(0, 2) + " " + 
								 fecha.substring(11, 13) + ":" + fecha.substring(14, 16) + ":00'}";
		}
		

		return fechaDB;
	}

	public static String formatoFechaDB(Calendar fecha) {
		if (fecha != null)
			return String.valueOf(String.valueOf((new StringBuffer("{ts '"))
					.append(String.valueOf(fecha.get(1))).append("-")
					.append(rellenarCeros(String.valueOf(fecha.get(2) + 1), 2))
					.append("-")
					.append(rellenarCeros(String.valueOf(fecha.get(5)), 2))
					.append(" ")
					.append(rellenarCeros(String.valueOf(fecha.get(11)), 2))
					.append(":")
					.append(rellenarCeros(String.valueOf(fecha.get(12)), 2))
					.append(":")
					.append(rellenarCeros(String.valueOf(fecha.get(13)), 2))
					.append("'}")));
		else
			return "null";
	}

	public static String formatoFechaDBSimple(Calendar fecha) {
		if (fecha != null)
			return String.valueOf(String.valueOf((new StringBuffer(String
					.valueOf(String.valueOf(String.valueOf(fecha.get(1))))))
					.append("-")
					.append(rellenarCeros(String.valueOf(fecha.get(2) + 1), 2))
					.append("-")
					.append(rellenarCeros(String.valueOf(fecha.get(5)), 2))
					.append(" ")
					.append(rellenarCeros(String.valueOf(fecha.get(11)), 2))
					.append(":")
					.append(rellenarCeros(String.valueOf(fecha.get(12)), 2))
					.append(":")
					.append(rellenarCeros(String.valueOf(fecha.get(13)), 2))));
		else
			return "null";
	}

	static final boolean arrayEquals(Object source[], Object target) {
		if (source == null)
			return target == null;
		if (!(target instanceof Object[])) {
			return false;
		} else {
			Object targ[] = (Object[]) target;
			return source.length == targ.length
					&& arrayRegionMatches(source, 0, targ, 0, source.length);
		}
	}

	static final boolean arrayEquals(int source[], Object target) {
		if (source == null)
			return target == null;
		if (!(target instanceof int[])) {
			return false;
		} else {
			int targ[] = (int[]) target;
			return source.length == targ.length
					&& arrayRegionMatches(source, 0, targ, 0, source.length);
		}
	}

	static final boolean arrayEquals(double source[], Object target) {
		if (source == null)
			return target == null;
		if (!(target instanceof double[])) {
			return false;
		} else {
			double targ[] = (double[]) target;
			return source.length == targ.length
					&& arrayRegionMatches(source, 0, targ, 0, source.length);
		}
	}

	static final boolean arrayEquals(Object source, Object target) {
		if (source == null)
			return target == null;
		if (source instanceof Object[])
			return arrayEquals((Object[]) source, target);
		if (source instanceof int[])
			return arrayEquals((int[]) source, target);
		if (source instanceof double[])
			return arrayEquals((int[]) source, target);
		else
			return source.equals(target);
	}

	static final boolean arrayRegionMatches(Object source[], int sourceStart,
			Object target[], int targetStart, int len) {
		int sourceEnd = sourceStart + len;
		int delta = targetStart - sourceStart;
		for (int i = sourceStart; i < sourceEnd; i++)
			if (!arrayEquals(source[i], target[i + delta]))
				return false;

		return true;
	}

	static final boolean arrayRegionMatches(int source[], int sourceStart,
			int target[], int targetStart, int len) {
		int sourceEnd = sourceStart + len;
		int delta = targetStart - sourceStart;
		for (int i = sourceStart; i < sourceEnd; i++)
			if (source[i] != target[i + delta])
				return false;

		return true;
	}

	static final boolean arrayRegionMatches(double source[], int sourceStart,
			double target[], int targetStart, int len) {
		int sourceEnd = sourceStart + len;
		int delta = targetStart - sourceStart;
		for (int i = sourceStart; i < sourceEnd; i++)
			if (source[i] != target[i + delta])
				return false;

		return true;
	}

	static final boolean objectEquals(Object source, Object target) {
		if (source == null)
			return target == null;
		else
			return source.equals(target);
	}

	static final String arrayToRLEString(short a[]) {
		StringBuffer buffer = new StringBuffer();
		buffer.append((char) (a.length >> 16));
		buffer.append((char) a.length);
		short runValue = a[0];
		int runLength = 1;
		for (int i = 1; i < a.length; i++) {
			short s = a[i];
			if (s == runValue && runLength < 65535) {
				runLength++;
			} else {
				encodeRun(buffer, runValue, runLength);
				runValue = s;
				runLength = 1;
			}
		}

		encodeRun(buffer, runValue, runLength);
		return buffer.toString();
	}

	static final String arrayToRLEString(byte a[]) {
		StringBuffer buffer = new StringBuffer();
		buffer.append((char) (a.length >> 16));
		buffer.append((char) a.length);
		byte runValue = a[0];
		int runLength = 1;
		byte state[] = new byte[2];
		for (int i = 1; i < a.length; i++) {
			byte b = a[i];
			if (b == runValue && runLength < 255) {
				runLength++;
			} else {
				encodeRun(buffer, runValue, runLength, state);
				runValue = b;
				runLength = 1;
			}
		}

		encodeRun(buffer, runValue, runLength, state);
		if (state[0] != 0)
			appendEncodedByte(buffer, (byte) 0, state);
		return buffer.toString();
	}

	private static final void encodeRun(StringBuffer buffer, short value,
			int length) {
		if (length < 4) {
			for (int j = 0; j < length; j++) {
				if (value == 42405)
					buffer.append('\uA5A5');
				buffer.append((char) value);
			}

		} else {
			if (length == 42405) {
				if (value == 42405)
					buffer.append('\uA5A5');
				buffer.append((char) value);
				length--;
			}
			buffer.append('\uA5A5');
			buffer.append((char) length);
			buffer.append((char) value);
		}
	}

	private static final void encodeRun(StringBuffer buffer, byte value,
			int length, byte state[]) {
		if (length < 4) {
			for (int j = 0; j < length; j++) {
				if (value == -91)
					appendEncodedByte(buffer, (byte) -91, state);
				appendEncodedByte(buffer, value, state);
			}

		} else {
			if (length == -91) {
				if (value == -91)
					appendEncodedByte(buffer, (byte) -91, state);
				appendEncodedByte(buffer, value, state);
				length--;
			}
			appendEncodedByte(buffer, (byte) -91, state);
			appendEncodedByte(buffer, (byte) length, state);
			appendEncodedByte(buffer, value, state);
		}
	}

	private static final void appendEncodedByte(StringBuffer buffer,
			byte value, byte state[]) {
		if (state[0] != 0) {
			char c = (char) (state[1] << 8 | value & 0xff);
			buffer.append(c);
			state[0] = 0;
		} else {
			state[0] = 1;
			state[1] = value;
		}
	}

	static final short[] RLEStringToShortArray(String s) {
		int length = s.charAt(0) << 16 | s.charAt(1);
		short array[] = new short[length];
		int ai = 0;
		for (int i = 2; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '\uA5A5') {
				c = s.charAt(++i);
				if (c == '\uA5A5') {
					array[ai++] = (short) c;
					continue;
				}
				int runLength = c;
				short runValue = (short) s.charAt(++i);
				for (int j = 0; j < runLength; j++)
					array[ai++] = runValue;

			} else {
				array[ai++] = (short) c;
			}
		}

		if (ai != length)
			throw new InternalError("Bad run-length encoded short array");
		else
			return array;
	}

	static final byte[] RLEStringToByteArray(String s) {
		int length = s.charAt(0) << 16 | s.charAt(1);
		byte array[] = new byte[length];
		boolean nextChar = true;
		char c = '\0';
		int node = 0;
		int runLength = 0;
		int i = 2;
		int ai = 0;
		do
			if (ai < length) {
				byte b;
				if (nextChar) {
					c = s.charAt(i++);
					b = (byte) (c >> 8);
					nextChar = false;
				} else {
					b = (byte) (c & 0xff);
					nextChar = true;
				}
				switch (node) {
				case 0: // '\0'
					if (b == -91)
						node = 1;
					else
						array[ai++] = b;
					break;

				case 1: // '\001'
					if (b == -91) {
						array[ai++] = -91;
						node = 0;
					} else {
						runLength = b;
						if (runLength < 0)
							runLength += 256;
						node = 2;
					}
					break;

				case 2: // '\002'
					for (int j = 0; j < runLength; j++)
						array[ai++] = b;

					node = 0;
					break;
				}
			} else {
				if (node != 0)
					throw new InternalError("Bad run-length encoded byte array");
				if (i != s.length())
					throw new InternalError(
							"Excess data in RLE byte array string");
				else
					return array;
			}
		while (true);
	}

	static final String formatForSource(String s) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < s.length(); buffer.append('"')) {
			if (i > 0)
				buffer.append("+\n");
			buffer.append("        \"");
			for (int count = 11; i < s.length() && count < 80;) {
				char c = s.charAt(i++);
				if (c < ' ' || c == '"') {
					buffer.append('\\');
					buffer.append(HEX_DIGIT[(c & 0x1c0) >> 6]);
					buffer.append(HEX_DIGIT[(c & 0x38) >> 3]);
					buffer.append(HEX_DIGIT[c & 7]);
					count += 4;
				} else if (c <= '~') {
					buffer.append(c);
					count++;
				} else {
					buffer.append("\\u");
					buffer.append(HEX_DIGIT[(c & 0xf000) >> 12]);
					buffer.append(HEX_DIGIT[(c & 0xf00) >> 8]);
					buffer.append(HEX_DIGIT[(c & 0xf0) >> 4]);
					buffer.append(HEX_DIGIT[c & 0xf]);
					count += 6;
				}
			}

		}

		return buffer.toString();
	}

	public static String getCadenaFecha(Calendar fecha) {
		String res = "";
		res = String.valueOf(String.valueOf((new StringBuffer(String
				.valueOf(String.valueOf(rellenarCeros(
						String.valueOf(fecha.get(5)), 2))))).append("/")
				.append(rellenarCeros(String.valueOf(fecha.get(2) + 1), 2))
				.append("/").append(String.valueOf(fecha.get(1)))));
		return res;
	}

	public static String getCadenaFechaLarga(Calendar fecha) {
		String res = "";
		String meses[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo",
				"Junio", "Julio", "Agosto", "Septiembre", "Octubre",
				"Noviembre", "Diciembre" };
		res = String.valueOf(String.valueOf((new StringBuffer(String
				.valueOf(String.valueOf(String.valueOf(fecha.get(5))))))
				.append(" de ").append(meses[fecha.get(2)]).append(" de ")
				.append(String.valueOf(fecha.get(1)))));
		return res;
	}

	public static String Repetir(String cadena, int veces) {
		String res = "";
		for (int i = 0; i < veces; i++)
			res = String.valueOf(String.valueOf(String.valueOf(res)))
					+ String.valueOf(String.valueOf(String.valueOf(cadena)));

		return res;
	}

	public static String getCadenaFechaMes(Calendar fecha) {
		String res = "";
		String meses[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo",
				"Junio", "Julio", "Agosto", "Septiembre", "Octubre",
				"Noviembre", "Diciembre" };
		res = meses[fecha.get(2)];
		return res;
	}

	public static String getCadenaFechaAlreves(Calendar fecha) {
		String res = "";
		res = String.valueOf(String.valueOf((new StringBuffer(String
				.valueOf(String.valueOf(String.valueOf(fecha.get(1))))))
				.append(rellenarCeros(String.valueOf(fecha.get(2) + 1), 2))
				.append(rellenarCeros(String.valueOf(fecha.get(5)), 2))));
		return res;
	}

	public static String FechaAlDerecho(String miFecha) {
		String res = "";
		res = miFecha.substring(8, 10) + "/" + miFecha.substring(5, 7) + "/"
				+ miFecha.substring(0, 4);
		return res;
	}

	public static String FechaAlreves(String miFecha) {
		String res = "";
		res = miFecha.substring(6, 10) + "/" + miFecha.substring(3, 5) + "/"
				+ miFecha.substring(0, 2);
		return res;
	}

	public static Calendar toCalendar(String xFecha) {
		String strFormat = "dd/MM/yyyy";
		System.out.println(xFecha);
		DateFormat myDateFormat = new SimpleDateFormat(strFormat);
		Date myDate = new Date();
		Calendar myGDate = new GregorianCalendar();
		try {
			myDate = myDateFormat.parse(xFecha);
			myGDate.setTime(myDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return myGDate;
	}

	
	
	public static String getCadenaFechaHora(Calendar fecha) {
		String res = "";
		res = String.valueOf(String.valueOf((new StringBuffer(String
				.valueOf(String.valueOf(rellenarCeros(
						String.valueOf(fecha.get(5)), 2))))).append("/")
				.append(rellenarCeros(String.valueOf(fecha.get(2) + 1), 2))
				.append("/").append(String.valueOf(fecha.get(1))).append(" ")
				.append(rellenarCeros(String.valueOf(fecha.get(11)), 2))
				.append(":")
				.append(rellenarCeros(String.valueOf(fecha.get(12)), 2))
				.append(":")
				.append(rellenarCeros(String.valueOf(fecha.get(13)), 2))));
		return res;
	}

	public static String getFechaDia(Calendar fecha) {
		String res = "";
		res = String.valueOf(fecha.get(Calendar.DAY_OF_MONTH));
		return res;
	}

	public static String getFechaMes(Calendar fecha) {
		String res = "";
		res = String.valueOf(fecha.get(Calendar.MONTH) + 1);
		return res;
	}

	public static String getFechaAno(Calendar fecha) {
		String res = "";
		res = String.valueOf(fecha.get(Calendar.YEAR));
		return res;
	}

	public static String getFechaHoras(Calendar fecha) {
		String res = "";
		res = String.valueOf(fecha.get(Calendar.HOUR_OF_DAY));
		return res;
	}

	public static String getMes(int xMes) {
		return mes[xMes];
	}

	public static String activar(int clave, String tabla, String campo) {
		String linea = "update " + tabla + " set activo=1 where " + campo + "="
				+ clave;
		UtilDB.ejecutaSQL(linea);
		return null;
	}

	public static String activar(String clave, String tabla, String campo) {
		String linea = "update " + tabla + " set activo=1 where " + campo + "="
				+ Comillas(clave);
		UtilDB.ejecutaSQL(linea);
		return null;
	}

	public static String Comillas(String valor) {
		String cad = valor;
		for (int i = valor.length() - 1; i >= 0; i--) {
			if (!valor.substring(i, i + 1).equals("'"))
				continue;
			if (i == 0)
				cad = "'".concat(String.valueOf(String.valueOf(cad)));
			else
				cad = String.valueOf(String.valueOf((new StringBuffer(String
						.valueOf(String.valueOf(cad.substring(0, i))))).append(
						"'").append(cad.substring(i, cad.length()))));
		}

		return new String(String.valueOf(String.valueOf((new StringBuffer("'"))
				.append(cad).append("'"))));

	}

	public static String desactivar(String clave, String tabla, String campo) {
		String linea = "update " + tabla + " set activo=0 where " + campo + "="
				+ Comillas(clave);
		UtilDB.ejecutaSQL(linea);
		return null;
	}

	public static String mover(String clave, int direccion, String tabla,
			String campo) {
		int lugarActual = 0;
		int lugarNuevo = 0;
		int lugarMaximo = 0;
		Resultados rs = null;

		String dir = direccion == 1 ? "desc" : "asc";
		String linea = "Select * From " + tabla + " order by orden " + dir;

		rs = UtilDB.ejecutaConsulta(linea);
		int encontrado = 0;
		int arriba = 0;

		if (direccion == 1 || direccion == 0) {
			while (rs.next()) {
				if (encontrado == 0) {
					if (rs.getString(campo).equals(Comillas(clave))) {
						encontrado = 1;
						lugarActual = rs.getInt("orden");
					}
				} else {
					arriba = rs.getInt(campo);
					lugarNuevo = rs.getInt("orden");
					break;
				}
			}
			if (arriba > 0) {
				linea = "update " + tabla + " set orden=" + lugarNuevo
						+ " where " + campo + "=" + Comillas(clave);
				UtilDB.ejecutaSQL(linea);

				linea = "update " + tabla + " set orden=" + lugarActual
						+ " where " + campo + "=" + arriba;
				UtilDB.ejecutaSQL(linea);
			}
		} else {
			// Hasta arriba
			if (direccion == 2) {
				linea = "select orden from " + tabla + " where " + campo + "="
						+ Comillas(clave);
				rs = UtilDB.ejecutaConsulta(linea);
				while (rs.next())
					lugarActual = rs.getInt("orden");

				linea = "update " + tabla
						+ " set orden=orden + 1 where orden <" + lugarActual;
				UtilDB.ejecutaSQL(linea);

				linea = "update " + tabla + " set orden=1 where " + campo + "="
						+ Comillas(clave);
				UtilDB.ejecutaSQL(linea);
			}
			// Hasta abajo
			if (direccion == 9) {
				linea = "select orden from " + tabla + " where " + campo + "="
						+ Comillas(clave);
				rs = UtilDB.ejecutaConsulta(linea);
				while (rs.next())
					lugarActual = rs.getInt("orden");

				linea = "select max(orden) as orden from " + tabla;
				rs = UtilDB.ejecutaConsulta(linea);
				while (rs.next())
					lugarMaximo = rs.getInt("orden");

				linea = "update " + tabla
						+ " set orden=orden - 1 where orden >" + lugarActual;
				UtilDB.ejecutaSQL(linea);
				System.out.println(linea);

				linea = "update " + tabla + " set orden=" + lugarMaximo
						+ " where " + campo + "=" + Comillas(clave);
				UtilDB.ejecutaSQL(linea);
				System.out.println(linea);
			}
		}

		return null;
	}	
	
	public static String desactivar(int clave, String tabla, String campo) {
		String linea = "update " + tabla + " set activo=0 where " + campo + "="
				+ clave;
		UtilDB.ejecutaSQL(linea);
		return null;
	}

	public static String mover(int clave, int direccion, String tabla,
			String campo) {
		int lugarActual = 0;
		int lugarNuevo = 0;
		int lugarMaximo = 0;
		Resultados rs = null;

		String dir = direccion == 1 ? "desc" : "asc";
		String linea = "Select * From " + tabla + " order by orden " + dir;

		rs = UtilDB.ejecutaConsulta(linea);
		int encontrado = 0;
		int arriba = 0;

		if (direccion == 1 || direccion == 0) {
			while (rs.next()) {
				if (encontrado == 0) {
					if (rs.getInt(campo) == clave) {
						encontrado = 1;
						lugarActual = rs.getInt("orden");
					}
				} else {
					arriba = rs.getInt(campo);
					lugarNuevo = rs.getInt("orden");
					break;
				}
			}
			if (arriba > 0) {
				linea = "update " + tabla + " set orden=" + lugarNuevo
						+ " where " + campo + "=" + clave;
				UtilDB.ejecutaSQL(linea);

				linea = "update " + tabla + " set orden=" + lugarActual
						+ " where " + campo + "=" + arriba;
				UtilDB.ejecutaSQL(linea);
			}
		} else {
			// Hasta arriba
			if (direccion == 2) {
				linea = "select orden from " + tabla + " where " + campo + "="
						+ clave;
				rs = UtilDB.ejecutaConsulta(linea);
				while (rs.next())
					lugarActual = rs.getInt("orden");

				linea = "update " + tabla
						+ " set orden=orden + 1 where orden <" + lugarActual;
				UtilDB.ejecutaSQL(linea);

				linea = "update " + tabla + " set orden=1 where " + campo + "="
						+ clave;
				UtilDB.ejecutaSQL(linea);
			}
			// Hasta abajo
			if (direccion == 9) {
				linea = "select orden from " + tabla + " where " + campo + "="
						+ clave;
				rs = UtilDB.ejecutaConsulta(linea);
				while (rs.next())
					lugarActual = rs.getInt("orden");

				linea = "select max(orden) as orden from " + tabla;
				rs = UtilDB.ejecutaConsulta(linea);
				while (rs.next())
					lugarMaximo = rs.getInt("orden");

				linea = "update " + tabla
						+ " set orden=orden - 1 where orden >" + lugarActual;
				UtilDB.ejecutaSQL(linea);
				System.out.println(linea);

				linea = "update " + tabla + " set orden=" + lugarMaximo
						+ " where " + campo + "=" + clave;
				UtilDB.ejecutaSQL(linea);
				System.out.println(linea);
			}
		}

		return null;
	}

	public static String getCadenaFechaCompletaCorta(Calendar fecha) {
		String res = "";
		String meses[] = { "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul",
				"Ago", "Sep", "Oct", "Nov", "Dic" };
		res = String.valueOf(fecha.get(Calendar.DAY_OF_MONTH)) + " de "
				+ meses[fecha.get(Calendar.MONTH)] + " de "
				+ String.valueOf(fecha.get(Calendar.YEAR));
		return res;
	}

	public static int UltimoDia(int xAno, int xMes) {
		int diasFebrero = 0;

		int dias = 0;
		if (xMes == 2) {
			String sqlFecha = "Select IsDate('29/02/" + String.valueOf(xAno)
					+ "') as valor";
			Resultados rsFecha = UtilDB.ejecutaConsulta(sqlFecha);
			int banderaFechaValida = 0;
			while (rsFecha.next()) {
				if (rsFecha.getInt("valor") == 1) {
					dias = 29;
				} else {
					dias = 28;
				}
			}
		}

		if (xMes == 1)
			dias = 31;
		if (xMes == 3)
			dias = 30;
		if (xMes == 4)
			dias = 30;
		if (xMes == 5)
			dias = 31;
		if (xMes == 6)
			dias = 30;
		if (xMes == 7)
			dias = 31;
		if (xMes == 8)
			dias = 31;
		if (xMes == 9)
			dias = 30;
		if (xMes == 10)
			dias = 31;
		if (xMes == 11)
			dias = 30;
		if (xMes == 12)
			dias = 31;

		return dias;
	}

	public static String getFechaMinutos(Calendar fecha) {
		String res = "";
		res = String.valueOf(fecha.get(Calendar.MINUTE));
		return res;
	}

	public static String getFechaSegundos(Calendar fecha) {
		String res = "";
		res = String.valueOf(fecha.get(Calendar.SECOND));
		return res;
	}

	public static String getCadenaHora(Calendar fecha) {
		String res = "";
		res = String.valueOf(String.valueOf((new StringBuffer(String
				.valueOf(String.valueOf(rellenarCeros(
						String.valueOf(fecha.get(11)), 2))))).append(":")
				.append(rellenarCeros(String.valueOf(fecha.get(12)), 2))
				.append(":")
				.append(rellenarCeros(String.valueOf(fecha.get(13)), 2))));
		return res;
	}

	public static String CantidadEnLetra(double Cantidad) {
		DecimalFormat nf = new DecimalFormat("00");
		double cantidad = 0.0D;
		double centavos = 0.0D;
		int digito = 0;
		int primerDigito = 0;
		int segundoDigito = 0;
		int tercerDigito = 0;
		int numeroBloques = 0;
		int bloqueCero = 0;
		String bloque = "";
		String cantidadEnLetra = "";
		Cantidad = (double) Math.round(Cantidad * Math.pow(10D, 2D))
				/ Math.pow(10D, 2D);
		cantidad = Math.floor(Cantidad);
		centavos = (Cantidad - cantidad) * 100D;
		String Unidades[] = { "UN", "DOS", "TRES", "CUATRO", "CINCO", "SEIS",
				"SIETE", "OCHO", "NUEVE", "DIEZ", "ONCE", "DOCE", "TRECE",
				"CATORCE", "QUINCE", "DIECISEIS", "DIECISIETE", "DIECIOCHO",
				"DIECINUEVE", "VEINTE", "VEINTIUN", "VEINTIDOS", "VEINTITRES",
				"VEINTICUATRO", "VEINTICINCO", "VEINTISEIS", "VEINTISIETE",
				"VEINTIOCHO", "VEINTINUEVE" };
		String Decenas[] = { "DIEZ", "VEINTE", "TREINTA", "CUARENTA",
				"CINCUENTA", "SESENTA", "SETENTA", "OCHENTA", "NOVENTA" };
		String Centenas[] = { "CIENTO", "DOSCIENTOS", "TRESCIENTOS",
				"CUATROCIENTOS", "QUINIENTOS", "SEISCIENTOS", "SETECIENTOS",
				"OCHOCIENTOS", "NOVECIENTOS" };
		numeroBloques = 1;
		do {
			primerDigito = 0;
			segundoDigito = 0;
			tercerDigito = 0;
			bloque = "";
			bloqueCero = 0;
			int i = 1;
			do {
				if (i > 3)
					break;
				digito = (int) cantidad % 10;
				if (digito != 0)
					switch (i) {
					case 1: // '\001'
						bloque = " ".concat(String.valueOf(String
								.valueOf(Unidades[digito - 1])));
						primerDigito = digito;
						break;

					case 2: // '\002'
						if (digito <= 2)
							bloque = " "
									.concat(String.valueOf(String
											.valueOf(Unidades[(digito * 10 + primerDigito) - 1])));
						else
							bloque = String.valueOf(String
									.valueOf((new StringBuffer(" "))
											.append(Decenas[digito - 1])
											.append(primerDigito == 0 ? " "
													: " Y").append(bloque)));
						segundoDigito = digito;
						break;

					case 3: // '\003'
						bloque = String
								.valueOf(String
										.valueOf((new StringBuffer(" "))
												.append(digito != 1
														|| primerDigito != 0
														|| segundoDigito != 0 ? Centenas[digito - 1]
														: "CIEN ").append(
														bloque)));
						tercerDigito = digito;
						break;
					}
				else
					bloqueCero++;
				cantidad = Math.floor(cantidad / 10D);
				if (cantidad == 0.0D)
					break;
				i++;
			} while (true);
			switch (numeroBloques) {
			case 1: // '\001'
				cantidadEnLetra = bloque;
				break;

			case 2: // '\002'
				cantidadEnLetra = String.valueOf(String
						.valueOf((new StringBuffer(String.valueOf(String
								.valueOf(bloque)))).append(
								bloqueCero != 3 ? " MIL" : " ").append(
								cantidadEnLetra)));
				break;

			case 3: // '\003'
				cantidadEnLetra = String.valueOf(String
						.valueOf((new StringBuffer(String.valueOf(String
								.valueOf(bloque)))).append(
								primerDigito != 1 || segundoDigito != 0
										|| tercerDigito != 0 ? " MILLONES"
										: " MILLON").append(cantidadEnLetra)));
				break;
			}
			numeroBloques++;
		} while (cantidad != 0.0D);
		return String.valueOf(String.valueOf((new StringBuffer("("))
				.append(cantidadEnLetra).append(" PESOS ")
				.append(nf.format(centavos)).append("/100 M.N. )")));
	}

	public static String inicialCadena(String cad) {
		char aux[] = cad.toLowerCase().toCharArray();
		StringBuffer c = new StringBuffer();
		boolean inicial = true;
		for (int i = 0; i < aux.length; i++) {
			if (inicial) {
				if (!String.valueOf(aux[i]).equals(" ")) {
					c.append(String.valueOf(aux[i]).toUpperCase());
					inicial = false;
				}
				continue;
			}
			if (String.valueOf(aux[i]).equals(" "))
				inicial = true;
			c.append(aux[i]);
		}

		return c.toString();
	}

	public static String CambiarCaracteres(String cadena, char caracterViejo,
			char caracterNuevo) {
		String nueva = "";

		for (int x = 0; x < cadena.length(); x++) {
			char caracter_actual = cadena.charAt(x);
			if (caracter_actual == caracterViejo) {
				nueva = nueva + caracterNuevo;
			} else {
				nueva = nueva + caracter_actual;
			}
		}
		return nueva.trim();
	}

	public static int ComparaFechas(Calendar fecha1, Calendar fecha2) {
		int comparador = 0;
		if (fecha1.get(1) == fecha2.get(1))
			comparador = 0;
		else if (fecha1.get(1) > fecha2.get(1))
			comparador = 1;
		else if (fecha1.get(1) < fecha2.get(1))
			comparador = 2;
		if (comparador == 0)
			if (fecha1.get(2) == fecha2.get(2))
				comparador = 0;
			else if (fecha1.get(2) > fecha2.get(2))
				comparador = 1;
			else if (fecha1.get(2) < fecha2.get(2))
				comparador = 2;
		if (comparador == 0)
			if (fecha1.get(5) == fecha2.get(5))
				comparador = 0;
			else if (fecha1.get(5) > fecha2.get(5))
				comparador = 1;
			else if (fecha1.get(5) < fecha2.get(5))
				comparador = 2;
		return comparador;
	}

	public static String palabras(String texto, int palabras) {
		String str = "";
		Vector ft = new Vector();
		ft = split(" ", texto);
		int filas = ft.size();
		if (palabras > filas)
			palabras = filas - 1;
		for (int x = 0; x <= palabras; x++) {
			str = str + ft.elementAt(x) + " ";
		}
		str = str.trim();
		return str;
	}

	public static String TiempoEnMes(String fecha_inicial, String fecha_final) {
		Vector ft = new Vector();
		Vector ft2 = new Vector();
		int anio = 0;
		int mes = 0;
		int dia = 0;
		int anio2 = 0;
		int mes2 = 0;
		int dia2 = 0;
		int res = 0;
		String tiempo = "";
		ft = split("/", fecha_inicial);
		anio = Integer.parseInt((String) ft.elementAt(2));
		mes = Integer.parseInt((String) ft.elementAt(1)) - 1;
		dia = Integer.parseInt((String) ft.elementAt(0));
		ft2 = split("/", fecha_final);
		anio2 = Integer.parseInt((String) ft2.elementAt(2));
		mes2 = Integer.parseInt((String) ft2.elementAt(1)) - 1;
		dia2 = Integer.parseInt((String) ft2.elementAt(0));
		if (anio == anio2 && mes == mes2)
			tiempo = "3 meses";
		if (anio == anio2 && mes != mes2) {
			res = mes - mes2;
			if (res <= 3)
				tiempo = "3 meses";
			if (res > 3 && res <= 6)
				tiempo = "6 meses";
			if (res > 6 && res <= 9)
				tiempo = "9 meses";
			if (res > 9 && res <= 12)
				tiempo = "1 a\361o";
		}
		if (anio < anio2)
			tiempo = "Mas de un a\361o";
		return tiempo;
	}

	public static String DesencriptarContrasena(String contrasena) {
		String original = "ABCDEFGHIJKLMN\321OPQRSTUVWXYZabcdefghijklmn\361opqrstuvwxyz1234567890!$%&/()=?\277\241,.-;:_ ";
		String encriptada = "Uqyh.-aJ,g4TPVDE/2WZ15uBC78b3X:_ 6AOHIYrstNFGvcj\361z\321def)=?\277\241omKL90!(i$%&QRklwxnMSp;";
		String temporal = "";
		label0: for (int x = 0; x < contrasena.length(); x++) {
			int y = 0;
			do {
				if (y >= encriptada.length())
					continue label0;
				char xContrasena = contrasena.charAt(x);
				char xOriginal = encriptada.charAt(y);
				if (xContrasena == xOriginal) {
					if ((x + 1) % 2 == 0) {
						temporal = String.valueOf(String.valueOf(String
								.valueOf(temporal)))
								+ String.valueOf(String.valueOf(String
										.valueOf(original.charAt(y))));
						continue label0;
					}
					if (y == original.length())
						temporal = String.valueOf(String.valueOf(String
								.valueOf(temporal)))
								+ String.valueOf(String.valueOf(String
										.valueOf(original.charAt(1))));
					else
						temporal = String.valueOf(String.valueOf(String
								.valueOf(temporal)))
								+ String.valueOf(String.valueOf(String
										.valueOf(original.charAt(y - 1))));
					continue label0;
				}
				y++;
			} while (true);
		}

		String cadenaEncriptada = temporal;
		return cadenaEncriptada;
	}

	public static String TiempoDetallado(String fecha_inicial,
			String fecha_actual) {
		Vector ft = new Vector();
		Vector ft2 = new Vector();
		int anio = 0;
		int mes = 0;
		int dia = 0;
		int anio2 = 0;
		int mes2 = 0;
		int dia2 = 0;
		int res = 0;
		int i = 0;
		int con = 0;
		String tiempo = "";
		ft = split("/", fecha_inicial);
		anio = Integer.parseInt((String) ft.elementAt(2));
		mes = Integer.parseInt((String) ft.elementAt(1)) - 1;
		dia = Integer.parseInt((String) ft.elementAt(0));
		ft2 = split("/", fecha_actual);
		anio2 = Integer.parseInt((String) ft2.elementAt(2));
		mes2 = Integer.parseInt((String) ft2.elementAt(1)) - 1;
		dia2 = Integer.parseInt((String) ft2.elementAt(0));
		if (anio < anio2) {
			con = 0;
			for (i = anio; i <= anio2; i++)
				con++;

			if (con == 1)
				tiempo = String.valueOf(String.valueOf(String.valueOf(tiempo)))
						+ String.valueOf(String.valueOf(String.valueOf(String
								.valueOf(String.valueOf(con))
								.concat(" a\361o "))));
			else
				tiempo = String.valueOf(String.valueOf(String.valueOf(tiempo)))
						+ String.valueOf(String.valueOf(String.valueOf(String
								.valueOf(String.valueOf(con)).concat(
										" a\361os "))));
		}
		if (mes < mes2) {
			con = 0;
			for (i = mes; i <= mes2; i++)
				con++;

			if (con == 1)
				tiempo = String.valueOf(String.valueOf(String.valueOf(tiempo)))
						+ String.valueOf(String.valueOf(String.valueOf(String
								.valueOf(String.valueOf(con)).concat(" mes "))));
			else
				tiempo = String.valueOf(String.valueOf(String.valueOf(tiempo)))
						+ String.valueOf(String.valueOf(String.valueOf(String
								.valueOf(String.valueOf(con)).concat(" meses "))));
		}
		if (dia < dia2) {
			con = 0;
			for (i = dia; i <= dia2; i++)
				con++;

			con--;

			if (con == 1)
				tiempo = String.valueOf(String.valueOf(String.valueOf(tiempo)))
						+ String.valueOf(String.valueOf(String.valueOf(String
								.valueOf(String.valueOf(con)).concat(" dia "))));
			else
				tiempo = String.valueOf(String.valueOf(String.valueOf(tiempo)))
						+ String.valueOf(String.valueOf(String.valueOf(String
								.valueOf(String.valueOf(con)).concat(" dias "))));
		}
		return tiempo;
	}


	public static String quitaCaracter(String cadena, String caracter,
			String cambio) {
		String acentos = caracter;
		String sinacentos = cambio;
		String nuevaCad = "";
		cadena.toLowerCase();
		for (int i = 0; i < cadena.length(); i++) {
			String letra = String.valueOf(cadena.charAt(i));
			int posicion = acentos.indexOf(cadena.charAt(i));
			if (posicion >= 0) {
				nuevaCad = nuevaCad + sinacentos.charAt(posicion);
			} else {
				nuevaCad = nuevaCad + letra;
			}
		}
		return nuevaCad;
	}

	public static String quitaAcentos(String cadena) {
		String acentos = "����������";
		String sinacentos = "aeiouAEIUO";
		String nuevaCad = "";
		cadena.toLowerCase();
		for (int i = 0; i < cadena.length(); i++) {
			String letra = String.valueOf(cadena.charAt(i));
			int posicion = acentos.indexOf(cadena.charAt(i));
			if (posicion >= 0) {
				nuevaCad = nuevaCad + sinacentos.charAt(posicion);
			} else {
				nuevaCad = nuevaCad + letra;
			}
		}
		return nuevaCad;
	}

	public static int eventos(int clave) {
		String sql = "Select distinct cve_evento from participante_evento where cve_participante="
				+ String.valueOf(clave);
		Resultados rs = UtilDB.ejecutaConsulta(sql);
		return rs.recordCount();
	}

	public static String sinAcentos(String dato) {
		String vocales = "\341\351\355\363\372aeiou";
		int i = 0;
		String nuevacad = "";
		for (; i < dato.length(); i++) {
			dato.toLowerCase();
			if (vocales.indexOf(dato.charAt(i)) >= 0)
				nuevacad = String.valueOf(String.valueOf(nuevacad)).concat("_");
			else
				nuevacad = String.valueOf(nuevacad)
						+ String.valueOf(dato.charAt(i));
		}

		return nuevacad;
	}

	public static String acentosHTML(String dato) {
		int i = 0;
		String nuevaCad = "";
		for (; i < dato.length(); i++) {
			char voc = dato.charAt(i);
			String letra = voc + "";

			if (letra.equals("�"))
				nuevaCad = nuevaCad + "&aacute;";
			else if (letra.equals("�"))
				nuevaCad = nuevaCad + "&eacute;";
			else if (letra.equals("�"))
				nuevaCad = nuevaCad + "&iacute;";
			else if (letra.equals("�"))
				nuevaCad = nuevaCad + "&oacute;";
			else if (letra.equals("�"))
				nuevaCad = nuevaCad + "&uacute;";
			else if (letra.equals("�"))
				nuevaCad = nuevaCad + "&Aacute;";
			else if (letra.equals("�"))
				nuevaCad = nuevaCad + "&Eacute;";
			else if (letra.equals("�"))
				nuevaCad = nuevaCad + "&Iacute;";
			else if (letra.equals("�"))
				nuevaCad = nuevaCad + "&Oacute;";
			else if (letra.equals("�"))
				nuevaCad = nuevaCad + "&Uacute;";
			else if (letra.equals("�"))
				nuevaCad = nuevaCad + "&ntilde;";
			else if (letra.equals("�"))
				nuevaCad = nuevaCad + "&Ntilde;";
			else
				nuevaCad = nuevaCad + letra;
		}
		return nuevaCad;
	}

	public static String encriptarContrasena(String contrasena) {
		String original = "ABCDEFGHIJKLMN\321OPQRSTUVWXYZabcdefghijklmn\361opqrstuvwxyz1234567890!$%&/()=?\277\241,.-;:_ ";
		String encriptada = "Uqyh.-aJ,g4TPVDE/2WZ15uBC78b3X:_ 6AOHIYrstNFGvcj\361z\321def)=?\277\241omKL90!(i$%&QRklwxnMSp;";
		String temporal = "";

		label0: for (int x = 0; x < contrasena.length(); x++) {
			int y = 0;
			do {
				if (y >= original.length())
					continue label0;
				char xContrasena = contrasena.charAt(x);
				char xOriginal = original.charAt(y);
				if (xContrasena == xOriginal) {
					if ((x + 1) % 2 == 0) {
						temporal = String.valueOf(temporal)
								+ String.valueOf(encriptada.charAt(y));
						continue label0;
					}
					if (y == encriptada.length())
						temporal = String.valueOf(temporal)
								+ String.valueOf(encriptada.charAt(1));
					else
						temporal = String.valueOf(temporal)
								+ String.valueOf(encriptada.charAt(y + 1));
					continue label0;
				}
				y++;
			} while (true);
		}

		String cadenaEncriptada = temporal;
		return cadenaEncriptada;
	}

	public static String mes[] = { "Enero", "Febrero", "Marzo", "Abril",
			"Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre",
			"Noviembre", "Diciembre" };
	static final char ESCAPE = '\uA5A5';
	static final byte ESCAPE_BYTE = -91;
	static final char HEX_DIGIT[] = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

}