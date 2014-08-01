package com.madilon.nefroconsultor.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class OtroMotivo implements Parcelable{
	private String titulo;
	private String descripcion;
	private int posicion;
	private Boolean checked;
	
	public OtroMotivo(String titulo, String descripcion, int posicion, boolean checked) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.posicion = posicion;
		this.setChecked(checked);
	}
	
	public OtroMotivo() {
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public Boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(titulo);
		dest.writeString(descripcion);
		dest.writeInt(posicion);
		dest.writeString(checked.toString());
	}
	
	public static final Parcelable.Creator<OtroMotivo> CREATOR = new Parcelable.Creator<OtroMotivo>() {
		public OtroMotivo createFromParcel(Parcel in) {
		    return new OtroMotivo(in);
		}
		
		public OtroMotivo[] newArray(int size) {
		    return new OtroMotivo[size];
		}
	};
	
	private OtroMotivo(Parcel in) {
	    titulo = in.readString();
	    descripcion = in.readString();
	    posicion = in.readInt();
	    checked = in.readString().equals("true") ? true:false;
	}
	
}
