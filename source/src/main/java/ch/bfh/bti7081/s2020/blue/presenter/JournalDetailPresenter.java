package ch.bfh.bti7081.s2020.blue.presenter;

import ch.bfh.bti7081.s2020.blue.domain.JournalEntry;
import ch.bfh.bti7081.s2020.blue.domain.dto.JournalEntryDto;
import ch.bfh.bti7081.s2020.blue.service.JournalEntryService;
import ch.bfh.bti7081.s2020.blue.util.BeanInjector;
import ch.bfh.bti7081.s2020.blue.view.journal.JournalDetailView;
import ch.bfh.bti7081.s2020.blue.view.journal.JournalDetailView.JournalDetailListener;

public class JournalDetailPresenter implements JournalDetailListener {

  private final JournalDetailView view;
  private final JournalEntryService journalEntryService;
  private JournalEntryDto model;

  public JournalDetailPresenter(JournalDetailView view, BeanInjector beanInjector) {
    this.view = view;
    this.journalEntryService = beanInjector.get(JournalEntryService.class);
  }

  @Override
  public void afterViewInit(Long id) {
    JournalEntry journalEntry = journalEntryService.findById(id);
    JournalEntryDto journalEntryDto = new JournalEntryDto(journalEntry.getId(), journalEntry.getTitle(), journalEntry.getContent());
    view.afterViewInit(journalEntryDto);
  }

  @Override
  public void onJournalEntryUpdate() {
    journalEntryService.update(model);
  }

  @Override
  public void onJournalEntryDelete() {
    view.showDeleteConfirmationDialog();
  }

  @Override
  public void onJournalEntryDeleteConfirm() {
    journalEntryService.deleteById(model.getId());
    view.hideDeleteConfirmationDialog();
    view.routeToHomeView();
  }

  @Override
  public void onJournalEntryDeleteCancel() {
    view.hideDeleteConfirmationDialog();
  }

  @Override
  public void setModel(JournalEntryDto journalEntryDto) {
    this.model = journalEntryDto;
  }
}
