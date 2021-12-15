package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    private static final Answer JAVAJIGI_A1 = AnswerTest.A1;
    private static final Answer SANJIGI_A2 = AnswerTest.A2;

    @Test
    @DisplayName("Answers 객체 생성")
    void create() {
        Answers answers = new Answers(Arrays.asList(JAVAJIGI_A1, SANJIGI_A2));
        assertThat(answers).isEqualTo(new Answers(Arrays.asList(JAVAJIGI_A1, SANJIGI_A2)));
    }

    @ParameterizedTest
    @MethodSource("provideUserForIsException")
    @DisplayName("다른 사람이 쓴 답변이 있음 -> CannotDeleteException 반환")
    void checkAnswersException(User loginUser) {
        Answers answers = new Answers(Arrays.asList(JAVAJIGI_A1, SANJIGI_A2));
        assertThatThrownBy(() -> answers.checkExistentAnswer(loginUser))
                .isInstanceOf(CannotDeleteException.class);
    }

    private static Stream<Arguments> provideUserForIsException() {
        return Stream.of(
                Arguments.of(UserTest.JAVAJIGI),
                Arguments.of(UserTest.SANJIGI)
        );
    }

    @ParameterizedTest
    @MethodSource("provideUserForIsOk")
    @DisplayName("다른 사람이 쓴 답변이 없음, 자기가 쓴 글 -> Validation 통과")
    void checkAnswersOK(Answer answer, User loginUser) {
        Answers answers = new Answers(answer);
        Assertions.assertThatCode(() -> answers.checkExistentAnswer(loginUser))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> provideUserForIsOk() {
        return Stream.of(
                Arguments.of(JAVAJIGI_A1, UserTest.JAVAJIGI),
                Arguments.of(SANJIGI_A2, UserTest.SANJIGI)
        );
    }

}
