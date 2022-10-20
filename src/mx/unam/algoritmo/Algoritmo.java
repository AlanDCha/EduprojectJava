package mx.unam.algoritmo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Algoritmo {

    // * This is the number generated
    private int[] number = new int[4];
    // * This is the number to compare
    private int[] compar = MixRandNums.mixDiffRandDigits();
    // * These are the counters for each attempt
    private int count_right = 0;
    private int count_wrong = 0;
    // * This matrix saves all the attempts and the counters too.
    private int[][] matrix = new int[9][4];
    // * This matrix saves all the wrong and right number of digits
    private ArrayList<Integer> counters_right = new ArrayList<>();
    private ArrayList<Integer> counters_wrong = new ArrayList<>();
    // * This is a flag that indicates if the whole number generated is right
    private boolean is_right = false;
    // * This array save all pinned numbers 
    private ArrayList<Integer> pinned_nmbs_index0 = new ArrayList<>();
    private ArrayList<Integer> pinned_nmbs_index1 = new ArrayList<>();
    private ArrayList<Integer> pinned_nmbs_index2 = new ArrayList<>();
    private ArrayList<Integer> pinned_nmbs_index3 = new ArrayList<>();
    // * This arrraylist matrix saves all not pinned numbers
    private ArrayList<Integer> not_pinned_nmbs_index0 = new ArrayList<>();
    private ArrayList<Integer> not_pinned_nmbs_index1 = new ArrayList<>();
    private ArrayList<Integer> not_pinned_nmbs_index2 = new ArrayList<>();
    private ArrayList<Integer> not_pinned_nmbs_index3 = new ArrayList<>();
    // * This array save all numbers to choose
    private ArrayList<Integer> to_choose = new ArrayList<>();
    // * This counter is for each intent
    private int intent = 1;
    // * This flag is to detect when it's needed to change strategy 
    private boolean is_four = false;
    // * Flag that detect if the intent 3 was repeated
    private boolean two_repeated = false;
    // * Flag that detect when it's needed to analize
    private boolean once_algorithm = false;
    // * Flag that detect when the 4 intent was repeated 4 times
    private boolean four_times = false;
    // * Flag that detect when the number has to be repeated 4 times
    private boolean repeated_four_times = false;
    // * Flag that detect when a digit has to be repeated 2 or 3 times
    private boolean two_or_three_times = false;
    // * Array where it is saved the secure digits
    private ArrayList<Integer> secure = new ArrayList<>();

    public Algoritmo(int[] number){
        this.number = number;
        for (int i = 0; i < 10; i++) to_choose.add(i);
    }

    public Algoritmo(){}

    public void showAlg(){
        System.out.println("-----------------");
        System.out.println("El digito es el siguiente");
        System.out.println(Arrays.toString(number));
        System.out.println("-----------------");
        while (intent <= 9) {
            System.out.println("||Ciclo: " + intent + " ||");
            if (checkNumEquals(number, compar)) {
                is_right = true;
                printOut(compar);
                break;
            } else {
                for (int i = 0; i < compar.length; i++) {
                    matrix[intent - 1][i] = compar[i];
                }
                counters_right.add(count_right);
                counters_wrong.add(count_wrong);
                printOut(compar);
                compar = modifyDigit(compar);
                intent++;
            } 
        }
        if (is_right){
            System.out.println("I did it :)");
        } else{
            System.out.println("I couldn't solve it. So sorry :(");
        }
    }

    public boolean checkNumEquals(final int[] first, int[] second){
        // * These are the prematch and match
        boolean[] prematch_first  = {false, false, false, false};
        boolean[] prematch_second = {false, false, false, false};
        boolean[] match_first  = {false, false, false, false};
        boolean[] match_second = {false, false, false, false};
        // Initializing counters
        count_right = 0;
        count_wrong = 0;
        ArrayList<Integer> aux_first = new ArrayList<>();
        boolean is_right_first = false;
        for (int i = 0; i < 4; i++) {
            if (MixRandNums.contains(first, second[i])){
                for (int j = 0; j < 4; j++) {
                    if (second[i] == first[j] && i == j) {
                        if (prematch_first[j] || prematch_second[i]){
                            prematch_first[j] = false;
                            prematch_second[i] = false;
                            if (is_right_first){
                                prematch_first[aux_first.get(0)] = false;
                            }
                            count_wrong--;
                            count_right++;
                            match_first[j] = true;
                            match_second[i] = true;
                        } else {
                            count_right++;
                            match_first[j] = true;
                            match_second[i] = true;
                        }
                    } else if (second[i] == first[j] && i != j) {
                        if ((!match_first[j] && !match_second[i])
                            && (!prematch_first[j] && !prematch_second[i])
                            && (first[j] != second[j])){

                            count_wrong++;
                            prematch_first[j]  = true;
                            prematch_second[i] = true;
                            aux_first.add(j);
                            is_right_first = true;
                        }
                    }
                }
            } 
            is_right_first = false;
            aux_first.clear();
        }
        return count_right == 4;
    }

    public void printOut(int[] two) {
        // Printing numbers
        System.out.println("Number generated");
        System.out.println(Arrays.toString(two));
        
        // Printing counters
        System.out.println("Digitos en posicion correcta: "   + count_right);
        System.out.println("Digitos en posicion incorrecta: " + count_wrong);
    }

    private int[] modifyDigit(int[] compare) {
        // Creating the modified number
        int[] modified = new int[4];
        // Highlighting the current counters
        int rnod = counters_right.get(intent - 1);
        int wnod = counters_wrong.get(intent - 1);
        // To sum wnod and rnod 
        int total = 0;

        // ! If W + R == 4. Focus on these digits and dropped the others
        if (wnod + rnod == 4 && !is_four){
            to_choose.clear();
            for (int i = 0; i < modified.length; i++) to_choose.add(compare[i]);
            is_four = true;
        }
        if (is_four){
            modified = sortDigits(compare);
        }
        if (wnod + rnod == 0){
            for (int i = 0; i < modified.length; i++) {
                to_choose.remove((Object)compare[i]);
            }
        }
        if (repeated_four_times){
            modified = repeatDigitFourTimes();
            return modified;
        }
        if (intent == 1){
            modified = MixRandNums.mixDiffRandDigits(compare);
            return modified;
        }
        if (intent == 2){
            for (int i = 0; i < intent; i++) {
                total = total + (counters_right.get(i) + counters_wrong.get(i));
            }
            if (total == 4){
                to_choose.clear();
                for (int i = 0; i < intent; i++) {
                    for (int j = 0; j < modified.length; j++) {
                        to_choose.add(matrix[i][j]); 
                    }
                }
                modified = algorithm();
                once_algorithm = true;
            } else {
                modified = repeatTwoDigitsInThree();
                two_repeated = true;
            }
        }
        if (intent == 3){
            total = 0;
            for (int i = 0; i < 3; i++) {
                total = total + (counters_right.get(i) + 
                    counters_wrong.get(i));
            }
            if (total == 1) { // * To repeat four times
                /* Example:
                 *  6824  01
                 *  1390  00
                 *  7575  00
                 */
                modified = repeatDigitFourTimes();
                four_times = true;
                return modified;
            } else if ((total > 1 && total < 4) && 
                (counters_right.get(intent - 1) + 
                counters_wrong.get(intent - 1) == 0)){
                /* Example:
                *  7041  20
                *  6928  01
                *  3535  00
                */
                two_or_three_times = true;
                modified = algorithm();
                once_algorithm = true;
                return modified;
            }
            // * flag that indicates if the prev intent was repeated: 6, 6, 8, 8
            if (two_repeated && 
                counters_right.get(intent - 1) + 
                counters_wrong.get(intent - 1) != 0){
                /* Example: 
                 * 7340  01
                 * 8921  11
                 * 5665  10
                 */
                modified = repeatDigitFourTimesIntentFour(compare);
                four_times = true;
            } else {
                modified = algorithm();
                once_algorithm = true;
            }
        }
        if (intent == 4){
            // * flag that indicates if the prev intent was repeated 4 times
            if (two_or_three_times){
                modified = algorithm();
                once_algorithm = true;
                return modified;
            }
            if (four_times){
                if (counters_right.get(intent - 1) +
                    counters_wrong.get(intent - 1) != 0){
                    /* Example:
                     *  7340  01
                     *  8921  11
                     *  5665  10
                     *  5555  10
                     * !! Drop 6 !!
                     */
                    int save = matrix[3][0];
                    // Save number to secure array
                    for (int i = 0; i < counters_right.get(3); i++) {
                        secure.add(save);
                    }
                    // Drop the other number
                    if (counters_right.get(2) + counters_wrong.get(2) ==
                    counters_right.get(3) + counters_wrong.get(3)){
                        for (int i = 0; i < modified.length; i++) {
                            if (matrix[2][i] != save){
                                to_choose.remove((Object)matrix[2][i]);
                                break;
                            }
                        }
                    }
                }
                if (counters_right.get(intent - 1) +
                    counters_wrong.get(intent - 1) == 0){
                    /* Example:
                     *  7340  01
                     *  8921  11
                     *  5665  10
                     *  5555  00
                     * !! Drop 5 !!
                     */
                    // Save number to secure array
                    int my_dropped = compare[0];
                    int save = 0;
                    for (int i = 0; i < modified.length; i++) {
                        if (matrix[2][i] != my_dropped){
                            save = matrix[2][i];
                            break;
                        }
                    }
                    for (int i = 0; i < (counters_right.get(3) +
                        counters_wrong.get(3)); i++) {
                        if (matrix[2][i] != my_dropped){
                            secure.add(save);
                        }
                    }
                }
                total = 0;
                for (int i = 0; i < 4; i++) {
                    total = total + (counters_right.get(i) + 
                        counters_wrong.get(i));
                    if (i == 2 && total == 4){
                        modified = algorithm();
                        once_algorithm = true;
                        return modified;
                    }
                    if ((i == 3 && total == 2) && (counters_right.get(i) +
                        counters_wrong.get(i) == 0) && 
                        (counters_right.get(i - 1) + 
                        counters_wrong.get(i - 1) == 2)){
                        /*
                         *  7340  00
                         *  8921  00
                         *  5665  02
                         *  5555  00
                         */
                        repeated_four_times = true;
                        modified = repeatDigitFourTimes();
                        return modified;
                    }
                    if (i == 3 && total == 1){
                        // repeat digits 4 times
                        /*
                         *  7340  00
                         *  8921  00
                         *  5665  10
                         *  6666  00
                         */
                        repeated_four_times = true;
                        modified = repeatDigitFourTimes();
                        return modified;
                    }
                }
            }
            modified = algorithm();
            once_algorithm = true;
        }
        if (intent >= 5) modified = algorithm();
        return modified;
    }

    public int[] algorithm(){
        // Taking care of array previous  and (R, W)
        int[] modified = new int[4];
        int count_R;
        int count_W;
        // int count_Ra = 0;
        // * Index to remove from matrix[2][]
        ArrayList<Integer> index = new ArrayList<>();
        ArrayList<Integer> index_added = new ArrayList<>();
        ArrayList<Integer> arr_index_it = new ArrayList<>();
        for (int i = 0; i < modified.length; i++) index.add(i);

        if (once_algorithm){
            unpinnedDigitsOnlyRights();
            pinnedDigitsOnlyRights();
            modified = pullPinnedArrayLists();
            for (int i = 0; i < modified.length; i++) {
                if (!checkEmptyPinnedArrayLists(i)) {
                    index.remove((Object)i);
                    index_added.add(i);
                }
            }
        } 
        for (int i = intent - 1; i >= 0; i--) {
            for (int j = 0; j < modified.length; j++) arr_index_it.add(j);
            count_R = counters_right.get(i);
            count_W = counters_wrong.get(i);
            if (count_R + count_W == 0) {
                arr_index_it.clear();
                continue;
            }
            // check if a digit into "n" array from matrix is repeated 4
            if (isRepeatedFourTimes(matrix[i])){
                arr_index_it.clear();
                continue;
            } 
            while (count_R > 0 || count_W > 0) {
                if (index.isEmpty()) break;
                if (count_R != 0) {
                    // Shuffle the modified index
                    Collections.shuffle(index);
                    // Adding the number from n matrix array being the same 
                    // position
                    for (int j = 0; j < modified.length; j++){
                        if (to_choose.contains(matrix[i][index.get(0)])){
                            modified[index.get(0)] = matrix[i][index.get(0)];
                            index_added.add(index.get(0));
                            index.remove(0);
                            count_R--;
                            break;
                        }
                        // * Continue with no pinned numbers
                    }
                    // Removing the index what can't be used anymore
                    // index_added.add(index.get(0));
                    // index.remove(0);
                    // count_R--;
                }
                if (index.isEmpty()) break;
                if (count_R == 0 && count_W != 0){
                    // Shuffle the modified index and the n array index
                    for (int j = 0; j < modified.length; j++) {
                        Collections.shuffle(arr_index_it);
                        Collections.shuffle(index);
                        if (to_choose.contains(matrix[i][arr_index_it.get(0)])
                            && index.get(0) != arr_index_it.get(0)){
                            // assign and remove the index in both variables
                            modified[index.get(0)] = 
                                matrix[i][arr_index_it.get(0)];
                            arr_index_it.remove(0);
                            index_added.add(index.get(0));
                            index.remove(0);
                            count_W--;
                            break;
                        }
                    }
                    // Remove the index
                    // index_added.add(index.get(0));
                    // index.remove(0);
                    // count_W--;
                }
            }
            arr_index_it.clear();
        }
        while (!index.isEmpty()) {
            // TODO: Check total digits to no repeat
            /**         R  W
             * 0 0 0 0  2  0
             * 0 0 9 0  2  0
             * Check also the generated number must be in diff position
             *          R  W
             * 1 8 0 3  0  1
             * 6 0 0 4  1  0
             */
            for (int i = 0; i < modified.length; i++) {
                Collections.shuffle(index_added);
                if (index.contains(i)){
                    // if (checkTotalDigits(index_added.get(0)) && 
                    //     diffPositionDigits()) {
                        modified[index.get(0)] = modified[index_added.get(0)];
                        break;
                    // }
                }
            }
            index_added.add(index.get(0));
            index.remove(0);
        }
        return modified;
    }

    public int[] sortDigits(int[] compar){
        // sort the digits taking care of matrix
        int[] modified = new int[4];
        ArrayList<Integer> index = new ArrayList<>();
        ArrayList<Integer> to_choose2 = to_choose;
        for (int i = 0; i < modified.length; i++) index.add(i);
        unpinnedDigitsOnlyRights();
        pinnedDigitsOnlyRights();
        modified = pullPinnedArrayLists();
        for (int i = 0; i < modified.length; i++) {
            if (!checkEmptyPinnedArrayLists(i)) index.remove((Object)i);
        }
        int i = 0;
        while (i < 4) {
            Collections.shuffle(to_choose2);
            boolean no_error = true;
            if (!index.contains(i)) {
                i++;
                continue;
            }
            if (!checkNotPinnedArrayLists(i, to_choose2.get(0))){
                for (int j = 0; j < intent; j++) {
                    if (matrix[j][i] == to_choose2.get(0) &&
                        counters_right.get(j) == 0){
                        no_error = false;
                        break;
                    }
                }
                if (no_error){
                    modified[i] = to_choose2.get(0);
                    to_choose2.remove(0);
                    index.remove((Object)i);
                    i++;
                }
            } 
        }
        return modified;
    }

    public int[] repeatDigitFourTimes(){
        int[] repeated = new int[4];
        Collections.shuffle(to_choose);
        for (int i = 0; i < repeated.length; i++) {
            repeated[i] = to_choose.get(0);
        }
        return repeated;
    }

    public int[] repeatTwoDigitsInThree(){
        int[] renewed = new int[4];
        ArrayList<Integer> reminded = new ArrayList<>();
        for (int i = 0; i < 10; i++) reminded.add(i);
        for (int i = 0; i < intent; i++) {
            for (int j = 0; j < renewed.length; j++) {
                reminded.remove((Object)matrix[i][j]);
            }
        }
        for (int i = 0; i < renewed.length; i++) {
            if (i >= 2){
                renewed[i] = reminded.get(1);
            } else {
                renewed[i] = reminded.get(0);
            }
        }
        return renewed;
    }

    public int[] repeatDigitFourTimesIntentFour(int[] compare){
        int[] repeated = new int[4];
        int item = 0;
        for (int iterable : compare) {
            if (to_choose.contains(iterable)){
                item = iterable;
                break;
            }
        }
        for (int i = 0; i < repeated.length; i++) {
            repeated[i] = item;
        }
        return repeated;
    }

    public boolean isRepeatedFourTimes(int[] array){
        for (int i = 1; i < array.length; i++) {
            if (array[i] != array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public void unpinnedDigitsOnlyRights(){
        int[] only_rights = new int[4];
        int attempt = 0;
        boolean found = false;
        int count = 0;
        while (found || count < intent - 1) {
            for (int i = count; i < intent; i++) {
                if (counters_right.get(i) != 0 && counters_wrong.get(i) == 0){
                    only_rights = matrix[i];
                    attempt = i + 1;
                    found = true;
                    break;
                }
            }
            if (found){
                for (int i = attempt + 1; i < intent; i++) {
                    if (counters_right.get(i) == 0 && 
                        counters_wrong.get(i) != 0){
                        for (int j = 0; j < only_rights.length; j++) {
                            if (only_rights[j] == matrix[i][j]){
                                addNumToNotPinnedArrayLists(j, 
                                    only_rights[j]);
                            }
                        }
                    }
                }
                count = attempt;
                found = false;
            } else {
                count++;
            }
        }
    }

    public void addNumToNotPinnedArrayLists(int index, int number){
        switch (index) {
            case 0:
                not_pinned_nmbs_index0.add(number);
                break;
            case 1:
                not_pinned_nmbs_index1.add(number);
                break;
            case 2:
                not_pinned_nmbs_index2.add(number);
                break;
            case 3:
                not_pinned_nmbs_index3.add(number);
                break;
            default:
                break;
        }
    }

    public void addNumToPinnedArrayLists(int index, int number){
        switch (index) {
            case 0:
                pinned_nmbs_index0.add(number);
                break;
            case 1:
                pinned_nmbs_index1.add(number);
                break;
            case 2:
                pinned_nmbs_index2.add(number);
                break;
            case 3:
                pinned_nmbs_index3.add(number);
                break;
            default:
                break;
        }
    }

    public boolean checkNotPinnedArrayLists(int index, int number){
        switch (index) {
            case 0:
                if (not_pinned_nmbs_index0.contains(number)) return true;
                break;
            case 1:
                if (not_pinned_nmbs_index1.contains(number)) return true;
                break;
            case 2:
                if (not_pinned_nmbs_index2.contains(number)) return true;
                break;
            case 3:
                if (not_pinned_nmbs_index3.contains(number)) return true;
                break;
            default:
                break;
        }
        return false;
    }

    public boolean checkEmptyNotPinnedArrayLists(){
        if (!not_pinned_nmbs_index0.isEmpty()) return false;
        if (!not_pinned_nmbs_index1.isEmpty()) return false;
        if (!not_pinned_nmbs_index2.isEmpty()) return false;
        if (!not_pinned_nmbs_index3.isEmpty()) return false;
        return true;
    }

    public void pinnedDigitsOnlyRights(){
        int[] only_rights = new int[4];
        int attempt = 0;
        boolean found = false;
        int count = 0;
        while (found || count < intent - 1) {
            for (int i = count; i < intent; i++) {
                if (counters_right.get(i) != 0 && counters_wrong.get(i) == 0) {
                    only_rights = matrix[i];
                    attempt = i + 1; // Beginning with 1
                    found = true;
                    break;
                }
            }
            if (found){
                for (int i = attempt; i < intent; i++) {
                    if (counters_right.get(i) != 0 &&
                        counters_wrong.get(i) == 0) {
                        for (int j = 0; j < only_rights.length; j++) {
                            if (only_rights[j] == matrix[i][j]){
                                addNumToPinnedArrayLists(j, 
                                    only_rights[j]);
                            }
                        }
                    }
                }
                count = attempt;
                found = false;
            } else {
                count++;
            }
        }
    }

    public boolean checkPinnedArrayLists(int index, int number){
        switch (index) {
            case 0:
                if (pinned_nmbs_index0.contains(number)) return true;
                break;
            case 1:
                if (pinned_nmbs_index1.contains(number)) return true;
                break;
            case 2:
                if (pinned_nmbs_index2.contains(number)) return true;
                break;
            case 3:
                if (pinned_nmbs_index3.contains(number)) return true;
                break;
            default:
                break;
        }
        return false;
    }

    public boolean checkEmptyPinnedArrayLists(int index){
        switch (index) {
            case 0:
                if (pinned_nmbs_index0.isEmpty()) return true;
                break;
            case 1:
                if (pinned_nmbs_index1.isEmpty()) return true;
                break;
            case 2:
                if (pinned_nmbs_index2.isEmpty()) return true;
                break;
            case 3:
                if (pinned_nmbs_index3.isEmpty()) return true;
                break;
        
            default:
                break;
        }
        return false;
    }

    public int[] pullPinnedArrayLists(){
        int[] array = new int[4];
        if (!pinned_nmbs_index0.isEmpty()) array[0] = pinned_nmbs_index0.get(0);
        if (!pinned_nmbs_index1.isEmpty()) array[1] = pinned_nmbs_index1.get(0);
        if (!pinned_nmbs_index2.isEmpty()) array[2] = pinned_nmbs_index2.get(0);
        if (!pinned_nmbs_index3.isEmpty()) array[3] = pinned_nmbs_index3.get(0);
        return array;
    }

}