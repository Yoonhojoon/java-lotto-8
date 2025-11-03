package lotto.service;

import java.util.ArrayList;
import java.util.List;

import lotto.Lotto;
import lotto.number.NumberGenerator;

public class LottoIssuer {

    public List<Lotto> issue(int purchaseCost, NumberGenerator generator) {
        int count = purchaseCost / 1000;
        List<Lotto> tickets = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            tickets.add(new Lotto(generator.generate()));
        }
        return tickets;
    }
}


