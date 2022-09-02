package GraphicsObjects;

/**
 * A class to implement Vector4f object
 * 
 * Variables: include float x,y,z,a, which represents the coordinates of this vector in the coordinate system.
 * Method: include PlusVector, MinusVector, PlusPoint, byScalar, NegateVector, length, Normal, dot and cross functions.
 * 
 * 
 * Purpose and details:
 * In terms of the calculation about vector and point, I encapsulate Vector + Vector, Vector - Vector, Vector + Point method
 * I do not design the method Vector - Point because it is not defined.
 *  
 * @author Wang Hewei
 */

public class Vector4f {

	public float x=0;
	public float y=0;
	public float z=0;
	public float a=0;
	
	public Vector4f() 
	{  
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
		a = 0.0f;
	}
	 
	public Vector4f(float x, float y, float z,float a) 
	{ 
		this.x = x;
		this.y = y;
		this.z = z;
		this.a = a;
	}
	
	// Implement Vector plus a Vector: vector object plus the x,y,z,a value in Additional vector, then return the new vector after plus vector operation.
	public Vector4f PlusVector(Vector4f Additonal) 
	{ 
		float Ax = Additonal.x;
		float Ay = Additonal.y;
		float Az = Additonal.z;
		float Aa = Additonal.a;
		return new Vector4f(x+Ax,y+Ay,z+Az,a+Aa);
	} 
	
	// Implement Vector minus a Vector: vector object minus the x,y,z,a value in Minus vector, then return the new vector after minus vector operation.
	public Vector4f MinusVector(Vector4f Minus) 
	{ 
		float Mx = Minus.x;
		float My = Minus.y;
		float Mz = Minus.z;
		float Ma = Minus.a;
		return new Vector4f(x-Mx,y-My,z-Mz,a-Ma);
	}
	
	// Implement Vector plus a Point: vector object plus the x,y,z,a value in Additional point, then return the new point after plus point operation.
	public Point4f PlusPoint(Point4f Additonal) 
	{ 
		float Ax = Additonal.x;
		float Ay = Additonal.y;
		float Az = Additonal.z;
		float Aa = Additonal.a;
		return new Point4f(x+Ax,y+Ay,z+Az,a+Aa);
	} 
	
	// Do not implement Vector minus a Point as it is undefined 
	
	// Implement a Vector * Scalar: We can do scalar multiplication by multiply the x,y,z,a by scale value, then return the new vector after these operations.
	public Vector4f byScalar(float scale)
	{
		return new Vector4f(x*scale,y*scale,z*scale,a*scale);
	}
	
	// Implement returning the negative of a Vector: x,y,z,a takes an opposite number, then use these three opposite number to construct a new vector.
	public Vector4f  NegateVector()
	{
		return new Vector4f(-x,-y,-z,-a);
	}
	
	// Implement getting the length of a Vector: calculate the magnitude of vector using pythagoras.
	public float length()
	{
		return (float) Math.sqrt(x*x+y*y+z*z+a*a);
	}
	
	// Implement getting the Normal of a Vector: multiply x,y,z,a in the vector by inverse of length, then create a new vector using the three results.
	// The method is returning a normalized vector with a one unity length. 
	public Vector4f Normal()
	{
		float Nx = x/length();
		float Ny = y/length();
		float Nz = z/length();
		float Na = a/length();
		return new Vector4f(Nx,Ny,Nz,Na);
	} 
	
	// Implement getting the dot product of Vector (scalar or inner product): It means compute a scalar from two vectors. The x,y,z,a values corresponding 
	// to the two vectors are multiplied and then added.
	public float dot(Vector4f v)
	{ 
		return ( this.x*v.x + this.y*v.y + this.z*v.z+ this.a*v.a);
	}
	
	// Implement getting the cross product of Vector X Vector: It means compute a vector from two vectors. This method can get a vector perpendicular 
	// to both lines of origin vector. 
	public Vector4f vector2(Vector4f v)  
	{ 
		float u0 = (this.y*v.z - z*v.y);
		float u1 = (z*v.x - x*v.z);
		float u2 = (x*v.y - y*v.x);
		float u3 = 0; //ignoring this for now  
		return new Vector4f(u0,u1,u2,u3);
	}
	
	// Implement getting the cross product of Vector X Vector: It means compute a vector from two vectors. This method can get a vector perpendicular 
	// to both lines of origin vector. 
	public Vector4f cross(Vector4f v)  
	{ 
		float u0 = (this.y*v.z - z*v.y);
		float u1 = (z*v.x - x*v.z);
		float u2 = (x*v.y - y*v.x);
		float u3 = 0; //ignoring this for now  
		return new Vector4f(u0,u1,u2,u3);
	}
}
	 

/*

										MMMM                                        
										MMMMMM                                      
 										MM MMMM                                    
 										MMI  MMMM                                  
 										MMM    MMMM                                
 										MMM      MMMM                              
  										MM        MMMMM                           
  										MMM         MMMMM                         
  										MMM           OMMMM                       
   										MM             .MMMM                     
MMMMMMMMMMMMMMM                        MMM              .MMMM                   
MM   IMMMMMMMMMMMMMMMMMMMMMMMM         MMM                 MMMM                 
MM                  ~MMMMMMMMMMMMMMMMMMMMM                   MMMM               
MM                                  OMMMMM                     MMMMM            
MM                                                               MMMMM          
MM                                                                 MMMMM        
MM                                                                   ~MMMM      
MM                                                                     =MMMM    
MM                                                                        MMMM  
MM                                                                       MMMMMM 
MM                                                                     MMMMMMMM 
MM                                                                  :MMMMMMMM   
MM                                                                MMMMMMMMM     
MM                                                              MMMMMMMMM       
MM                             ,MMMMMMMMMM                    MMMMMMMMM         
MM              IMMMMMMMMMMMMMMMMMMMMMMMMM                  MMMMMMMM            
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM               ZMMMMMMMM              
MMMMMMMMMMMMMMMMMMMMMMMMMMMMM          MM$             MMMMMMMMM                
MMMMMMMMMMMMMM                       MMM            MMMMMMMMM                  
  									MMM          MMMMMMMM                     
  									MM~       IMMMMMMMM                       
  									MM      DMMMMMMMM                         
 								MMM    MMMMMMMMM                           
 								MMD  MMMMMMMM                              
								MMM MMMMMMMM                                
								MMMMMMMMMM                                  
								MMMMMMMM                                    
  								MMMM                                      
  								MM                                        
                             GlassGiant.com */