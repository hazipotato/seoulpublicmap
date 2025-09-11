package com.example.lbs.service;

import com.example.lbs.common.ApiException;
import com.example.lbs.domain.PlaceList;
import com.example.lbs.dto.list.CreateListRequest;
import com.example.lbs.dto.list.ListSummaryDto;
import com.example.lbs.dto.list.UpdateListRequest;
import com.example.lbs.repository.PlaceListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
@Transactional
public class ListService {

    private final PlaceListRepository repo;

    public ListSummaryDto create(CreateListRequest req) {
        PlaceList saved = repo.save(PlaceList.builder()
                .name(req.getName())
                .build());
        return new ListSummaryDto(saved.getId(), saved.getName());
    }

    @Transactional(readOnly = true)
    public List<ListSummaryDto> findAll() {
        return repo.findAll().stream()
                .map(l -> new ListSummaryDto(l.getId(), l.getName()))
                .toList();
    }

    @Transactional(readOnly = true)
    public ListSummaryDto findOne(Long id) {
        PlaceList l = repo.findById(id)
                .orElseThrow(() -> new ApiException(404, "리스트가 없습니다."));
        return new ListSummaryDto(l.getId(), l.getName());
    }

    public ListSummaryDto update(Long id, UpdateListRequest req) {
        PlaceList l = repo.findById(id)
                .orElseThrow(() -> new ApiException(404, "리스트가 없습니다."));
        l.setName(req.getName()); // 변경감지
        return new ListSummaryDto(l.getId(), l.getName());
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new ApiException(404, "리스트가 없습니다.");
        repo.deleteById(id);
    }
}