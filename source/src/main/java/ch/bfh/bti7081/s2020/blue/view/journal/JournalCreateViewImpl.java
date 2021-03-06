package ch.bfh.bti7081.s2020.blue.view.journal;

import ch.bfh.bti7081.s2020.blue.domain.dto.JournalEntryDto;
import ch.bfh.bti7081.s2020.blue.presenter.JournalCreatePresenter;
import ch.bfh.bti7081.s2020.blue.util.BeanInjector;
import ch.bfh.bti7081.s2020.blue.view.layout.SocialAnxietyLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("journal/create")
@CssImport("./styles.css")
public class JournalCreateViewImpl extends SocialAnxietyLayout implements JournalCreateView {

  private JournalCreatePresenter listener;

  public JournalCreateViewImpl(BeanInjector beanInjector) {
    super(beanInjector);
  }

  @Override
  protected void initializeView(BeanInjector beanInjector) {
    var binder = new Binder<>(JournalEntryDto.class);
    JournalEntryDto journalEntry = new JournalEntryDto();
    binder.setBean(journalEntry);
    add(createFormLayout(binder));
    listener = new JournalCreatePresenter(journalEntry, this, beanInjector);
  }

  private FormLayout createFormLayout(Binder<JournalEntryDto> binder) {
    FormLayout formlayout = new FormLayout();

    TextField title = new TextField();
    formlayout.addFormItem(title, "Titel");
    binder.bind(title, JournalEntryDto::getTitle, JournalEntryDto::setTitle);

    TextArea content = new TextArea();
    content.getStyle().set("minHeight", "150px");
    formlayout.addFormItem(content, "Inhalt");
    binder.bind(content, JournalEntryDto::getContent, JournalEntryDto::setContent);

    Button createButton = new Button("Erstellen");
    createButton.addClickListener(event -> listener.onJournalEntryCreate());
    createButton.getStyle().set("cursor", "pointer");
    formlayout.add(createButton);

    return formlayout;
  }

  @Override
  public void navigateToDetailView(Long journalId) {
    getUI().ifPresent(ui -> ui.navigate(JournalDetailViewImpl.class, journalId));
  }
}
