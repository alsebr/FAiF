

public class HeroStat {
public double strp,vitp,intp,agip,lckp;
public HeroStat(double strp, double vitp,double intp,double agip,double lckp){
	this.strp=strp;
	this.vitp=vitp;
	this.intp=intp;
	this.agip=agip;
	this.lckp=lckp;
}

static public HeroStat summ(HeroStat a, HeroStat b) {
	// TODO Auto-generated constructor stub
	return new HeroStat(a.strp+b.strp, a.vitp+b.vitp, a.intp+b.intp,a.agip+b.agip,a.lckp+b.lckp);
}
	


}
