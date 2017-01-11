package ariel.kcg.goingo.kcgmath;

/**
 * Created by VLad Landa on 12/22/2016.
 */

public class Vector3d {

    double _x, _y, _z;

    public Vector3d(double x, double y, double z) {
        _x =x;
        _y =y;
        _z =z;
    }

    public Vector3d(Vector3d v) {
        _x =v._x;
        _y =v._y;
        _z =v._z;
    }

    public Vector3d() {
        this(0,0,0);
    }

    public double dot(Vector3d u) {
        return _x *u._x +
                _y *u._y +
                _z *u._z;
    }

    public void set_y(double _y) {
        this._y = _y;
    }

    public void set_z(double _z) {
        this._z = _z;
    }

    public void set_x(double _x) {
        this._x = _x;
    }

    public double getX() {
        return _x;
    }

    public double getY() {
        return _y;
    }

    public double getZ() {
        return _z;
    }

    public void scaleBy(double scale){
        _x*=scale;
        _y*=scale;
        _z*=scale;
    }

    public Vector3d cross(Vector3d u) {
        double x = _y*u._z-_z*u._y;
        double y = _z*u._x-_x*u._z;
        double z = _x*u._y-_y*u._x;
        return new Vector3d(x,y,z);
    }

    public Vector3d getInstance(){
        return new Vector3d(this);
    }

    public Vector3d plus(Vector3d v) {
        return new Vector3d(_x+v._x,_y+v._y,_z+v._z);
    }

    public Vector3d minus(Vector3d v) {
        return new Vector3d(_x-v._x,_y-v._y,_z-v._z);
    }

    public double getNorm(){
        return Math.sqrt(_x*_x+_y*_y+_z*_z);
    }

    public double angle(Vector3d v) throws Exception {
        double dot = this.dot(v);
        double norms = getNorm()*v.getNorm();
        if(norms == 0)throw new Exception("this or v : getNorm() is 0");
        double angle = Math.acos(dot/norms);
        if(v.cross(this)._z > 0)
            return angle;
        return 2*Math.PI - angle;
    }

    public Vector3d projectionOn(Vector3d v) throws Exception {
        double normV = v.getNorm();
        if(normV == 0)throw new Exception("v : getNorm() is 0");
        double dot = this.dot(v);
        Vector3d project = new Vector3d(v);
        project.scaleBy(dot/(normV*normV));
        return project;
    }

    public void normalized(){
        double norm = getNorm();
        if(norm == 0)return;
        norm = 1/norm;
        _x*=norm;_y*=norm;_z*=norm;
    }

    public double[] getArray(){
        return new double[]{_x,_y,_z};
    }

    public void setMinus(Vector3d v){
        _x-=v._x;_y-=v._y;_z-=v._z;
    }

    public void setPlus(Vector3d v){
        _x+=v._x;_y+=v._y;_z+=v._z;
    }

    public void setDivition(double factor){
        if(factor!=0){
            double f = 1/factor;
            _x*=f;_y*=f;_z*=f;
        }
    }

    @Override
    public String toString() {
        return "Vector3d { "+ String.format( "%.5f", _x ) + " , "
                + String.format( "%.5f", _y ) + " , "
                + String.format( "%.5f", _z )+" } ";
    }
}
