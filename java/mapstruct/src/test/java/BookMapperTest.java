import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BookMapperTest {

	@Test
	public void bookMapper() {

		Book book = new Book();
		book.setTitle("ぼくの≤mapper");

		BookDto bookDto = BookMapperImpl.INSTANCE.BookToBookDto(book);

		Assertions.assertThat(bookDto.getTitle()).isEqualTo(book.getTitle());
	}
}