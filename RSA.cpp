#include <iostream>
#include <math.h>
#include <string>
#include <vector>
using namespace std;
long long int getPrimeNumber(int a){ //Criba de Eratostenes
    vector <int> Primes;
    for (int i=2; i<a; i++) 
        for (int j=2; j*j<=i; j++)
        {
            if (i % j == 0) 
                break;
            else if (j+1 > sqrt(i)) {
                Primes.push_back(i);
            }
        }
        int randomPrime = rand() % Primes.size();
        return Primes[randomPrime];
    }

long long int MCD(long long int e, long long int phi){ //Maximo Comun Divisor
    long long int temp;
    while(true){
        temp = e%phi;
        if(temp == 0){
            return phi;
        }
        e = phi;
        phi = temp;
    }

}
long long int inversoMultiplicativo(long long int e, long long int phi){ //Algoritmo euclidiano extendido
   long long int i = 1;
   long  long int x;
    while(true){
        x = (e*i-1)%phi;
        if(x==0){
            return i;
        }
        i++;
    }
}
long long int exponentiation(long long int base, long long int exponent, int modulus) //Exponenciación Modular
{
    long long int result = 1;
    while (exponent > 0)
    {
        if (exponent % 2 == 1)
            result = (result * base) % modulus;
        exponent = exponent >> 1;
        base = (base * base) % modulus;
    }
    return result;
}
class User{
    public:
    User(long long int P, long long int Q){
        this->setPublicKeyN(P,Q);
        this->setPhi(P,Q);
        this->setPublicKeyE();
        this->setPrivateKey();
    }
    void setPublicKeyN(long long int P, long long int Q){
        n = P*Q;
    }
    long long int getPublicKeyN(){
        return n;
    }
    long long int getPublicKeyE(){
        return e;
    }
    void setPublicKeyE(){
        e = 2; 
       long long int phiTemp = getPhi(); 
        while (e < phiTemp) 
            { 
        if (MCD(e, phiTemp)==1) 
            break; 
        else
            e++; 
        }   
    }
    void setPrivateKey(){
        d = inversoMultiplicativo(this->getPublicKeyE(), this->getPhi());
    }
    long long int getPrivateKey(){
        return d;
    }
    void setPhi(long long int P, long long int Q){
        phi = (P-1)*(Q-1);
    }
    long long int getPhi(){
        return phi;
    }
    private:
    long long int n, e; //Llave publica
    long long int d; // Llave privada 
    long long int phi; //Funcion totem de Euler

};

class RSA{
    public:
            RSA(){

            }
        string EncryptData(User* u, string msg){
            int msgArr[msg.length()];
            string encryptedData="";
            string decryptedData = "";
            for(int i = 0; i<sizeof(msgArr)/sizeof(msgArr[0]); i++){
                msgArr[i] = int(msg[i]);
                msgArr[i] = exponentiation(msgArr[i],u->getPublicKeyE(),u->getPublicKeyN());
                encryptedData+=msgArr[i];
            }
            cout << "El mensaje encriptado es: " << encryptedData << endl;

        for(int i = 0; i<sizeof(msgArr)/sizeof(msgArr[0]); i++){
            msgArr[i] = exponentiation(msgArr[i], u->getPrivateKey(), u->getPublicKeyN());
            decryptedData+=msgArr[i];
        }   
        cout << "El mensaje original es: " << decryptedData << endl;         
        return decryptedData;
        }


};



int main(){
    long int x = getPrimeNumber(1000);
    long int y = getPrimeNumber(1000);
    User* u = new User(x,y);
    RSA* rsa = new RSA();


    string h = "Hola Richie, mi tarjeta de credito es: 5579 1000 3456 8901";
    string f = rsa->EncryptData(u,h);



    return 0;
}