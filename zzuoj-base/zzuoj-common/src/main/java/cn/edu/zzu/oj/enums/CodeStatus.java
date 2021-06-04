package cn.edu.zzu.oj.enums;

public enum CodeStatus {
    // Accepted
    JudgeFlagAC(0, "Accepted"),
    // Presentation Error
    JudgeFlagPE(1, "Presentation Error"),
    // Time Limit Exceeded
    JudgeFlagTLE(2, "Time Limit Exceeded"),
    // Memory Limit Exceeded
    JudgeFlagMLE(3, "Memory Limit Exceeded"),
    // Wrong Answer
    JudgeFlagWA(4, "Wrong Answer"),
    // Runtime Error
    JudgeFlagRE(5,"Runtime Error"),
    // Output Limit Exceeded
    JudgeFlagOLE(6,"Output Limit Exceeded"),
    // Compile Error
    JudgeFlagCE(7, "Compile Error"),
    // System Error
    JudgeFlagSE(8, "System Error"),
    // 9 is for ReJudge
    // Special Judge Checker Time OUT
    JudgeFlagSpecialJudgeTimeout(10, "special judge tle"),
    // Special Judge Checker ERROR
    JudgeFlagSpecialJudgeError(11, "special judge error"),
    // Special Judge Checker Finish, Need Standard Checkup
    JudgeFlagSpecialJudgeRequireChecker(12, "special judge require checker");

    private final int value;

    private final String msg;

    CodeStatus(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int value() {
        return this.value;
    }

    public String getMsg() {
        return this.msg;
    }

    //todo: 下面的逻辑有问题，之后改吧。
    public static CodeStatus getCodeStatusById(int p){
        CodeStatus[] temp = CodeStatus.values();
        for(int i=0; i<temp.length; i++){
            if(p == temp[i].value){
                return temp[i];
            }
        }
        return temp[0];
    }

}
