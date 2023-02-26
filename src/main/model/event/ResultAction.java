package model.event;

public abstract class ResultAction implements GameAction {
    private int result; // 1 means successful result, otherwise results in failure unless handled in action

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public void action() {
        switch (result) {
            case 0:
                failure();
                return;
            case 1:
                success();
                return;
            default:
                return;
        }
    }

    protected abstract void success();

    protected abstract void failure();
}
