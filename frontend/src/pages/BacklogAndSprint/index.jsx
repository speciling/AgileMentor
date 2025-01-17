import React, { useState } from 'react';
import styled from 'styled-components';
// eslint-disable-next-line import/no-unresolved
import Story from '@components/common/Story/index';
// eslint-disable-next-line import/no-unresolved
import Sprint from '@components/common/Sprint/index';
// eslint-disable-next-line import/no-unresolved
import Backlog from '@components/common/Backlog/index';
import { useProjects } from '../../provider/projectContext';

const BacklogAndSprintPage = () => {
  const { projects, selectedProjectId } = useProjects();
  const [showOnlyMyTasks, setShowOnlyMyTasks] = useState(false);

  const selectedProjectTitle =
    projects.find((project) => project.projectId === selectedProjectId)
      ?.title || '프로젝트 선택하기';

  const toggleMyTasks = () => {
    setShowOnlyMyTasks((prev) => !prev);
  };

  return (
    <PageContainer>
      <MainContent>
        <HeaderContainer>
          <Title>{selectedProjectTitle}</Title>
          <Subtitle>백로그 및 스프린트</Subtitle>
        </HeaderContainer>
        <ContentContainer>
          <StoryContainer>
            <Story projects={projects} />
          </StoryContainer>
          <SprintSection>
            <ButtonContainer>
              <MyTasksButton onClick={toggleMyTasks}>
                <Checkbox type="checkbox" checked={showOnlyMyTasks} readOnly />
                내 작업만 보기
              </MyTasksButton>
              <AIRecommendationButton>
                <StarIcon>⭐</StarIcon>
                AI 추천
              </AIRecommendationButton>
            </ButtonContainer>
            <SprintContainer>
              <Sprint />
              <Sprint />
              <Sprint />
            </SprintContainer>
            <BacklogContainer>
              <Backlog />
            </BacklogContainer>
          </SprintSection>
        </ContentContainer>
      </MainContent>
    </PageContainer>
  );
};

export default BacklogAndSprintPage;

const PageContainer = styled.div`
  display: flex;
  height: calc(100vh - 9vh);
`;

const MainContent = styled.main`
  position: relative;
  width: calc(100vw - 18vw);
  height: calc(100vh - 9vh);
  background-color: #fafafa;
  padding: 0 20px;
  overflow-y: auto;
  overflow-x: auto;
  color: #333;
`;

const HeaderContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-left: 3rem;
  margin-top: 2vh;
`;

const Title = styled.h1`
  font-weight: bold;
  font-size: 2rem;
  color: #333;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
`;

const Subtitle = styled.h2`
  font-weight: normal;
  font-size: 1.2rem;
  color: #3a3a3a;
  margin-top: 0.3rem;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
`;

const ContentContainer = styled.div`
  display: flex;
  gap: 1rem;
  margin-top: 1rem;
`;

const StoryContainer = styled.div`
  flex: 1;
  margin-left: 3rem;
`;

const SprintSection = styled.div`
  flex: 2;
  display: flex;
  flex-direction: column;
  gap: 1rem;
  margin-right: 3rem;
  position: relative;
`;

const ButtonContainer = styled.div`
  display: flex;
  gap: 1rem;
  position: absolute;
  top: -3rem;
`;

const MyTasksButton = styled.button`
  display: flex;
  align-items: center;
  background-color: #80a7f0;
  color: #fff;
  font-size: 1rem;
  font-weight: bold;
  border: none;
  border-radius: 8px;
  padding: 0.6rem 1.2rem;
  cursor: pointer;
  box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.1);

  &:hover {
    opacity: 0.9;
  }
`;

const AIRecommendationButton = styled.button`
  display: flex;
  align-items: center;
  background-color: #ffe7b5;
  color: #9146ff;
  font-size: 1rem;
  font-weight: bold;
  border: none;
  border-radius: 8px;
  padding: 0.6rem 1.2rem;
  cursor: pointer;
  box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.1);

  &:hover {
    opacity: 0.9;
  }
`;

const Checkbox = styled.input`
  appearance: none;
  margin-right: 0.5rem;
  width: 1rem;
  height: 1rem;
  border: 2px solid #fff;
  border-radius: 4px;
  background-color: ${(props) => (props.checked ? '#FFF' : 'transparent')};
  cursor: pointer;

  &:checked {
    background-color: #fff;
  }

  &:focus {
    outline: none;
  }
`;

const StarIcon = styled.span`
  font-size: 1rem;
  color: #9146ff;
  margin-right: 0.5rem;
`;

const SprintContainer = styled.div`
  height: 35vh;
  overflow-y: auto;
  display: flex;
  flex-direction: column;
  gap: 1rem;
  padding-right: 0.5rem;
`;

const BacklogContainer = styled.div`
  height: 35vh;
  border-radius: 8px;
  flex-direction: column;
  gap: 1rem;
`;
