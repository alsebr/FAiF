

public class HeroStat {
public double strp,vitp,intp;
public HeroStat(double strp, double vitp,double intp){
	this.strp=strp;
	this.vitp=vitp;
	this.intp=intp;
}

static public HeroStat summ(HeroStat a, HeroStat b) {
	// TODO Auto-generated constructor stub
	return new HeroStat(a.strp+b.strp, a.vitp+b.vitp, a.intp+b.intp);
}
	


}
