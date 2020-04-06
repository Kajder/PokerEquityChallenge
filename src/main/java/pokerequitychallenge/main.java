package pokerequitychallenge;

public class main {

    public static void main(String[] args) {
        Table table = new Table();
    }
}

//TODO stworzyć własne wyjątki i obstawiać nimi kod z góry na dół (szczegołnie dużo przy liczeniu rąk,
// bo każda metoda powinna być zabezpieczona przed podaniem błędnych argumentów


//todo każdy player ma hand (maksymalny z dostępnych hand (max z 5 z 7)
//todo table ma playerów i pulę z niedookreślonymi kartami
//todo game to jest table z rozlosowanymi kartami (playerzy i pula są zapełnione przez metode losoującą z decku)
//todo czyli po każdym game (symulacji jednego rozdania) spisywani są zwycięzcy i przed kolejnym game'em
// przywracane są układy z table

//todo czyli po wejściu w pętlę games, dla każdego game trzeba:
//dobrać brakujące karty (te których użytkownik postanowił nie ustawiać)
//wybrać maksymalną hand dla każdego gracza i mu ją przypisać (tutaj przyda się jakaś statyczna metoda)
//wyłonić zwycięzców i zapisać ich wygraną do licznika poza pętlą games
//przywrócić oryginalnie ustawione (przez użytkownika) karty graczom i puli
//przejść do kolejnej iteracji pętli