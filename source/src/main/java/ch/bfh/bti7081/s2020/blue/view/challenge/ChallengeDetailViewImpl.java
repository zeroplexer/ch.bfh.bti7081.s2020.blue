package ch.bfh.bti7081.s2020.blue.view.challenge;

import ch.bfh.bti7081.s2020.blue.domain.Challenge;
import ch.bfh.bti7081.s2020.blue.presenter.ChallengeDetailPresenter;
import ch.bfh.bti7081.s2020.blue.util.BeanInjector;
import ch.bfh.bti7081.s2020.blue.view.HomeView;
import ch.bfh.bti7081.s2020.blue.view.layout.SocialAnxietyLayout;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route("challenge")
@CssImport("./styles.css")
public class ChallengeDetailViewImpl extends SocialAnxietyLayout implements ChallengeDetailView, HasUrlParameter<Long> {

  private final ChallengeDetailViewListener listener;
  private Div contentDiv;
  private Label loadingLabel;

  public ChallengeDetailViewImpl(BeanInjector beanInjector) {
    super(beanInjector);

    listener = new ChallengeDetailPresenter(this, beanInjector);
  }

  @Override
  protected void initializeView(BeanInjector beanInjector) {
    loadingLabel = new Label("Loading challenge..");

    contentDiv = new Div();
    contentDiv.add(loadingLabel);

    add(contentDiv);
  }

  @Override
  public void setParameter(BeforeEvent beforeEvent, Long id) {
    listener.onInit(id);
  }

  @Override
  public void display(Challenge challenge) {
    contentDiv.remove(loadingLabel);

    H3 name = new H3(challenge.getName());
    name.getStyle().set("margin", "0");

    Text content = new Text(challenge.getContent());

    Checkbox criteriaCheckbox = new Checkbox(challenge.getCriteria());

    Button challengeCompleteButton = new Button("Herausforderung abschliessen");
    challengeCompleteButton.getStyle().set("cursor", "pointer");
    challengeCompleteButton.addClickListener(event -> listener.onChallengeComplete(challenge.getId()));

    contentDiv.add(name, content, newSeparator(), criteriaCheckbox, newSeparator(), challengeCompleteButton);
  }

  private Html newSeparator() {
    return new Html("<br />");
  }

  @Override
  public void afterChallengeCompleted() {
    getUI().ifPresent(ui -> ui.navigate(HomeView.class));
  }
}
