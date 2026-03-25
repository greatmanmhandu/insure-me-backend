package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.Impl;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.Help;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository.HelpRepository;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.HelpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelpServiceImpl implements HelpService {

    private final HelpRepository helpRepository;
    @Override
    public ResponseEntity<BaseResult> saveHelp(Help help) {
        return ResponseEntity.ok(new BaseResult(helpRepository.save(help), "saved  successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllHelpList() {
        return ResponseEntity.ok(new BaseResult(helpRepository.findAll(), "help fetched successfully","00",200));

    }
}

