package com.nox.catch_a_meteor.layers;

import com.nox.catch_a_meteor.R;
import com.nox.catch_a_meteor.base.Lists;
import com.nox.catch_a_meteor.base.TimeConstants;
import com.nox.catch_a_meteor.control.AstronomerModel;
import com.nox.catch_a_meteor.model.SpaceObjectObservation;
import com.nox.catch_a_meteor.source.AbstractAstronomicalSource;
import com.nox.catch_a_meteor.source.AstronomicalSource;
import com.nox.catch_a_meteor.source.LineSource;
import com.nox.catch_a_meteor.source.TextSource;
import com.nox.catch_a_meteor.source.impl.LineSourceImpl;
import com.nox.catch_a_meteor.source.impl.TextSourceImpl;
import com.nox.catch_a_meteor.units.GeocentricCoordinates;
import com.nox.catch_a_meteor.units.RaDec;
import com.nox.catch_a_meteor.units.Vector3;

import android.content.res.Resources;
import android.graphics.Color;
import android.text.format.DateFormat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link Layer} to show the user's observed meteors.
 *
 * @author Guillaume Prevost
 */
public class SpaceObjectObservationLayer extends AbstractSourceLayer {
  private List<SpaceObjectObservation> spaceObjectObservations = Lists.newArrayList();

  private final AstronomerModel model;
  
  private static final int LINE_COLOR = Color.argb(255, 0, 220, 0);
  
  public SpaceObjectObservationLayer(AstronomerModel model, Resources resources, List<SpaceObjectObservation> spaceObjectObservations) {
    super(resources, true);
    this.model = model;
    this.spaceObjectObservations = spaceObjectObservations;
  }
  
  @Override
  protected void initializeAstroSources(ArrayList<AstronomicalSource> sources) {
    for (SpaceObjectObservation spaceObjectObservation : spaceObjectObservations) {
      sources.add(new MeteorRadiantSource(model, spaceObjectObservation, getResources()));
    }
  }

  @Override
  public int getLayerId() {
    return -106;
  }

  @Override
  public String getPreferenceId() {
    return "source_provider.6";
  }

  @Override
  public String getLayerName() {
    return "Meteor observations";
  }

  @Override
  protected int getLayerNameId() {
    return R.string.show_obs_layer_pref;
  }

  private static class MeteorRadiantSource extends AbstractAstronomicalSource {
    private static final int LABEL_COLOR = 0xf67e81;
    private static final Vector3 UP = new Vector3(0.0f, 1.0f, 0.0f);
    private static final long UPDATE_FREQ_MS = 1L * TimeConstants.MILLISECONDS_PER_DAY;

    private final AstronomerModel model;

    private SpaceObjectObservation spaceObjectObservation;
    private final ArrayList<LineSourceImpl> lineSources = new ArrayList<LineSourceImpl>();
    private final ArrayList<TextSourceImpl> textSources = new ArrayList<TextSourceImpl>();
        
    private String name;
    private List<String> searchNames = Lists.newArrayList();

    public MeteorRadiantSource(AstronomerModel model, SpaceObjectObservation spaceObjectObservation, Resources resources) {
      this.model = model;
      this.spaceObjectObservation = spaceObjectObservation;
      this.name = spaceObjectObservation.getTitle();

      CharSequence dateObserved = DateFormat.format("MMM dd", spaceObjectObservation.getDateObserved());
      searchNames.add(name + " (" + dateObserved + ")");

      LineSourceImpl line = new LineSourceImpl(LINE_COLOR);
	  RaDec raDec = new RaDec(spaceObjectObservation.getRa(), spaceObjectObservation.getDec());
      line.raDecs.add(raDec);
      line.vertices.add(spaceObjectObservation.getCoordinates());
      
	  raDec = new RaDec(spaceObjectObservation.getRaEnd(), spaceObjectObservation.getDecEnd());
      line.raDecs.add(raDec);
      line.vertices.add(spaceObjectObservation.getCoordinatesEnd());
      
      lineSources.add(line);
      
      textSources.add(new TextSourceImpl(spaceObjectObservation.getCoordinates(), name, LABEL_COLOR));
    }

    @Override
    public List<String> getNames() {
      return searchNames;
    }

    @Override
    public GeocentricCoordinates getSearchLocation() {
      return spaceObjectObservation.getCoordinates();
    }
    
    @Override
    public List<? extends TextSource> getLabels() {
      return textSources;
    }

    @Override
    public List<? extends LineSource> getLines() {
      return lineSources;
    }
  }
}
