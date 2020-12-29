import java.math.BigDecimal;
import java.util.ArrayList;

class Clock {

    //dokladnosc do 1 s
    static double calculateAngle(double h, double m, double s){
        if(s == 60) {
            m += 1;
            s = 0;
        }
        if(m==60){
            h += 1;
            m =0;
        }
        if(h==12)
            h=0;
        if(h>12)
            h = h-12;

        //kąty wskazówek obliczane dla konkretnej godziny minuty i sekundy
        double hourAngle = 0.5/60 * (h*60*60 +m*60+s); //kąt wskazówki godzinowej

        // 6/60 = 0.1
        double minuteAngle = 0.1 * (m*60 + s); //kąt wskazówki minutowej

        double angle = Math.abs(hourAngle-minuteAngle);
        angle = Math.abs(Math.min(360-angle,angle));
        return angle;
    }

    //dokladnosc do 1 ms
    static BigDecimal calculateAngle(double h, double m, double s, double ms){
        if(ms == 1000) {
            s += 1;
            ms = 0;
        }
        if(s == 60) {
            m += 1;
            s = 0;
        }
        if(m==60){
            h += 1;
            m =0;
        }
        if(h==12)
            h=0;
        if(h>12)
            h = h-12;


        BigDecimal hAngle = new BigDecimal(0.5/60/1000 * (h*60*60*1000 +m*60*1000+s*1000));
        //double hourAngle = 0.5/60/1000 * (h*60*60*1000 +m*60*1000+s*1000); //kąt wskazówki godzinowej

        BigDecimal mAngle = new BigDecimal(0.1/1000 * (m*60*1000 + s*1000));
        //double minuteAngle = 0.1/1000 * (m*60*1000 + s*1000); //kąt wskazówki minutowej

        BigDecimal rAngle;


        BigDecimal help = new BigDecimal(360);
        rAngle = new BigDecimal(String.valueOf((hAngle.subtract(mAngle).abs())));
        rAngle = (rAngle.min(help.subtract(rAngle))).abs();

        //rAngle = Math.abs(Math.min(360-angle,angle));
        return rAngle;
    }

    // dokladnosc do 1 s
    // h,m,s - odpowiada wybranej godzinie, n - ilosc sekund ktore ma minac w pomiarze, epsilon - dokladnosc bledu
    static ArrayList findAngle(int h, int m, int s, int n, double epsilon){
        ArrayList listOfAnswers = new ArrayList<Double>();

        for (int i = 0; i < n; i++){
            double answer = calculateAngle(h, m, s); //oblicza kąt
            //decyduje czy wybrana godzina spelnia
            if (answer < epsilon){

                //zamiana sekund na konkretna godzine
                int sec = s;
                int min = m;
                int hou = h;
                while (sec > 60){
                    sec -=60;
                    min += 1;
                }
                while (min > 60){
                    min -=60;
                    hou += 1;
                }


                String answerText = ("Kąt " + answer + " odpowiada godzinie: " + hou + " h " + min + " m " + sec + " s");
                listOfAnswers.add(answerText);
            }
            s++;
        }
        return listOfAnswers;
    }

    // dokladnosc do 1 ms
    // h,m,s - odpowiada wybranej godzinie, n - ilosc sekund ktore ma minac w pomiarze, epsilon - dokladnosc bledu
    static ArrayList findAngle(int h, int m, int s, int ms, int n, double epsilon){
        ArrayList listOfAnswers = new ArrayList<Double>();

        for (int i = 0; i < n; i++){
            BigDecimal answer = calculateAngle(h, m, s, ms); //oblicza kąt
            //decyduje czy wybrana godzina spelnia
            if (answer.compareTo(BigDecimal.valueOf(epsilon)) == -1){

                //zamiana sekund na konkretna godzine
                int mil = ms;
                int sec = s;
                int min = m;
                int hou = h;
                while (mil >1000){
                    mil -= 1000;
                    sec += 1;
                }
                while (sec > 60){
                    sec -=60;
                    min += 1;
                }
                while (min > 60){
                    min -=60;
                    hou += 1;
                }


                String answerText = ("Kąt " + answer + " odpowiada godzinie: " + hou + " h " + min + " m " + sec + " s " + mil + " ms");
                listOfAnswers.add(answerText);
            }
            ms++;
        }
        return listOfAnswers;
    }


     public static void main(String[] args) {

        // Wypisuje kąt (tylko mniejszy niż zadana dokładność jako epsilon) między wskazówkami zegara  jaki powstaje w ciągu 2h (7200 sekund od godz. 12:00)

         int h = 12;
         int m = 0;
         int s = 0;
         int n = 7200;

         ArrayList list, list2, list3;
         list = findAngle(h,m,s,n,0.1);
         for (Object x : list){
             System.out.println(x);
         }

         System.out.println("\nKolejne wyniki po poprawieniu dokładności na podstawie wczesniejszych wyliczeń\n");

         //wybieramy dokładność większą, czyli liczba niższa od kąta dla godziny 12:00:01
         list2 = findAngle(h,m,s+1,n-1,0.09);
         for (Object x : list2){
             System.out.println(x);
         }

         System.out.println("\nPo określeniu, że wskazówki pokrywają się miedzy godziną 13:05, a godziną 13:06" +
                 " sprawdzamy kąty przy każdej milisekundzie\n");

         //sprawdzamy kąt dla kazdej milisekundy miedzy 13:05:27 a 13:05:28
         list3 = findAngle(13,5,27,0, 1000,0.025);
         for (Object x : list3){
             System.out.println(x);
         }

         //Niestety nawet z uzyciem klasy BigDecimal nie jestesmy w stanie otrzymac wiekszej dokladnosci
         // i wyliczyc milisekundy dla której kąt byłby wyraźnie niższy niż dla 13:05:27 i 13:05:28
     }
}
