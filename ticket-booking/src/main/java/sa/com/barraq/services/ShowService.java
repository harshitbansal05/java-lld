package sa.com.barraq.services;

import lombok.NonNull;
import sa.com.barraq.exceptions.NotFoundException;
import sa.com.barraq.exceptions.ScreenAlreadyOccupiedException;
import sa.com.barraq.model.*;

import java.util.*;

public class ShowService {

    private final Map<String, Show> shows;

    public ShowService() {
        this.shows = new HashMap<>();
    }

    public Show getShow(@NonNull final String showId) {
        if (!shows.containsKey(showId)) {
            throw new NotFoundException();
        }
        return shows.get(showId);
    }

    public Show createShow(@NonNull final Movie movie, @NonNull final Screen screen, @NonNull final Date startTime,
                           @NonNull final Integer durationInSeconds) {
        if (!checkIfShowCreationAllowed(screen, startTime, durationInSeconds)) {
            throw new ScreenAlreadyOccupiedException();
        }
        String showId = UUID.randomUUID().toString();
        final Show show = new Show(showId, movie, screen, startTime, durationInSeconds);
        this.shows.put(showId, show);
        return show;
    }

    private List<Show> getShowsForScreen(final Screen screen) {
        final List<Show> response = new ArrayList<>();
        for (Show show : shows.values()) {
            if (show.getScreen().equals(screen)) {
                response.add(show);
            }
        }
        return response;
    }

    private boolean checkIfShowCreationAllowed(final Screen screen, final Date startTime, final Integer durationInSeconds) {
        return true;
    }
}
