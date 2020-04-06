package racingcar;

import java.util.*;

public class RacingController {
    public static final int FIRST = 1;
    public static final int NUMBER_GENERATE_RANGE = 10;
    private static final String CAR_NAME_REQUIRED_ERROR = "자동차 이름은 값이 존재해야 합니다.";

    private int tryCount;
    private Cars cars;
//    private Map<Integer, List<Integer>> record;
    private Record record;

    public RacingController(InputView inputView) {
        this.cars = createCar(inputView.getCarNames());
        tryCount = inputView.getTryCount();
        record = new Record();
    }

    public void startGame() {
        gameStart();
        showResult();
    }

    private List<Integer> changePosition() {
        Cars move = cars.move();
        cars = move;
        return move.getPositions();
    }

    private Cars createCar(String carNames) {
        List<Car> tempCars = new ArrayList<>();

        if (StringUtils.isBlank(carNames)) {
            throw new IllegalArgumentException(CAR_NAME_REQUIRED_ERROR);
        }
        String[] names = carNames.split(",");

        for (String name : names) {
            tempCars.add(new Car(name));
        }
        return new Cars(tempCars);
    }

    private void gameStart() {
        for (int count = FIRST; count <= tryCount; count++) {
            record.add(count, changePosition());
        }
    }

    private void showResult() {
        ResultView resultView = new ResultView(record, this.cars);
        resultView.show();
    }


}
