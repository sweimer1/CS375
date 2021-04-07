EXE_PATH=`pwd`
PROGRAM="$EXE_PATH/$1"
PROGRAM_PATH="${PROGRAM%/*}"
TESTCASE_PATH="${EXE_PATH}/testcases"
case "$2" in
   "java") echo "execuating java program $PROGRAM" 
    EXEC="java ${PROGRAM##*/}"
   ;;
   *) echo "testing c/c++ program $PROGRAM" 
    EXEC="./${PROGRAM##*/}"
   ;;
esac
#echo $PROGRAM
#echo $EXEC
cd $PROGRAM_PATH
T_IN="testcase_in.txt"
T_OUT="testcase_out.txt"
DIFF_C="diff -y -w -i -W 200"
score=100

#testcase for 9
  clear
  echo $score
  cat "$TESTCASE_PATH/9in.txt" > $T_IN
  $EXEC $T_IN $T_OUT
  $DIFF_C $T_OUT "$TESTCASE_PATH/9out.txt"
  echo "Does case 9 passed?(y/n):"
