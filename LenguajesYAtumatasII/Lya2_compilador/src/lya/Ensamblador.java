package lya;
 
public class Ensamblador {
private String num;
private String op;

public Ensamblador(String num,String op) {
	this.num=num;
	this.op=op;
}

public String getNum() {
	return num;
}

public void setNum(String num) {
	this.num = num;
}

public String getOp() {
	return op;
}

public void setOp(String op) {
	this.op = op;
}

}
