package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.response;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.ShiftLogin;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private User user;
    private ShiftLogin shiftLogin;
}

