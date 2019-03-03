
package astrolife;
import static java.lang.Math.*;


public class Sun {
  
    public double X=57.2957795130823,JD,T,L0,M0,e0,E=23.45;
    
    
    public double Longitude(int y,int m,int d,int h,int mi){
       int A,B;
      double  D;
       double lon,om;
       D=d+(h+mi/60)/24;
       A=(int) Math.floor(y/100);
       B= 2-A+(int)Math.floor(A/4);
       JD= (int) Math.floor(365.25*(y+4716)) + (int) Math.floor(30.6001*(m+1))
               +D+B-1524.5;
        T=(JD-2451545)/36525;
        
        L0=280.46646+36000.76983*T+0.0003032*T*T;
        M0=367.52911+35999.05029*T-0.0001537*T*T;
        e0=0.016708634-0.000042037*T-0.0000001267*T*T;
        while(L0>360){
            L0=L0-360;
        }
        while(L0<0){
            L0=L0+360;
        }
        while(M0>360){
            M0=M0-360;
        }
        while(M0<0){
            M0=M0+360;
        }
        double tmp;
        tmp =(1.914602-0.004817*T-0.000014*T*T)*sin(M0/X)+(0.019993-0.000101*T)*sin(2*M0/X)
        +0.000289*sin(3*M0/X);
        om=125.04-1934.136*T;
        lon=L0+tmp-0.00569-0.00478*sin(om/X);
        if(y>2000) {
            lon=lon-0.01397*(y-2000);
        }
       double H= h*60+mi;
       H=5*H/(60*360);
      
        return (lon+H);
    }
    
    public double Dcl(int y,int m,int d,int h,int mi){
        
        double j=Longitude( y, m, d, h, mi);
        return asin(sin(E/X)*sin(j/X))*X;
        
    }
        public double RA(int y,int m,int d,int h,int mi){
        
        double j=Longitude( y, m, d, h, mi);
        double r= atan2(cos(E/X)*sin(j/X),cos(j/X))*X;
        if(r<0) r=r+360;
        return r;
        
    }
}
