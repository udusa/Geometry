public class Quaternion {

    private double _w, _i, _j, _k;
    public Quaternion() {
        this(0,0,0,0);
    }
    public Quaternion(Quaternion q) {
        this(q._w,q._i,q._j,q._k);
    }
    public Quaternion(double _w, double _i, double _j, double _k) {
        this._w = _w;
        this._i = _i;
        this._j = _j;
        this._k = _k;
        
    }
    public double get_k() {
        return _k;
    }
    public void set_k(double _k) {
        this._k = _k;
    }
    public double get_J() {
        return _j;
    }
    public void set_J(double j) {
        this._j = j;
    }
    public double get_I() {
        return _i;
    }
    public void set_I(double _i) {
        this._i = _i;
    }
    public double get_W() {
        return _w;
    }
    public void set_W(double w) {
        this._w = w;
    }

    public Quaternion getConjugate(){
        return new Quaternion(_w,-_i,-_j,-_k);
    }

    public Quaternion quatProduct(Quaternion q){
        //this = q1, q = q2
        Quaternion result = new Quaternion();
        double wSample = _w, wRotation = q._w;
        Vector3d v = new Vector3d(_i, _j, _k);
        Vector3d u = new Vector3d(q._i, q._j, q._k);
        result.set_W(wSample * wRotation - v.dot(u));
        Vector3d uScale = new Vector3d(wSample * u.getX(), wSample * u.getY(), wSample * u.getZ());
        Vector3d vScale = new Vector3d(wRotation * v.getX(), wRotation * v.getY(), wRotation * v.getZ());
        Vector3d vCrossU = v.cross(u);
        Vector3d fin = (uScale.plus(vScale)).plus(vCrossU);
        result.set_I(fin.getX());
        result.set_J(fin.getY());
        result.set_k(fin.getZ());
        return result;
    }

    public Vector3d rotateVector(Vector3d v){
        Quaternion vec = new Quaternion(0,v.getX(),v.getY(),v.getZ());
        Quaternion conj = getConjugate();
        Quaternion result = conj.quatProduct(vec).quatProduct(this);
        return new Vector3d(result._i,result._j,result._k);
    }


    @Override
    public String toString() {
        return "Quaternion{" + _w + "," + _i + "," + _j + "," + _k + '}';
    }
}