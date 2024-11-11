import React from 'react';

import { Typography, Divider } from '@mui/material';
import styled from 'styled-components';
// eslint-disable-next-line import/extensions
import SideMenu from '../../components/features/SideBar';
import ExternalLinkButtons from '../../components/common/ExternalLinkButtons';
// eslint-disable-next-line import/no-named-as-default
import ProjectList from '../../components/common/ProjectList';
import OngoingTasksList from '../../components/common/OngoingTasksList';

const DashboardPage = () => (
  <DashboardContainer>
    <FixedSideMenu>
      <SideMenu />
    </FixedSideMenu>

    <MainContent>
      <HeaderContainer>
        <ProjectInfo>
          <Typography variant="subtitle1" sx={{ color: '#333' }}>
            Project Name:
          </Typography>
          <Typography
            variant="h4"
            sx={{ fontWeight: 'bold', color: '#333', fontSize: '4rem' }}
          >
            Project B
          </Typography>
        </ProjectInfo>
        <ExternalLinkContainer>
          <ExternalLinkButtons />
        </ExternalLinkContainer>
      </HeaderContainer>

      <ContentSection>
        <StyledPaper>
          <SectionHeader>
            <Typography variant="h6" sx={{ fontWeight: 'bold' }}>
              My Projects
            </Typography>
            <Divider sx={{ mb: 0 }} />
          </SectionHeader>
          <SectionContent>
            <ProjectList />
          </SectionContent>
        </StyledPaper>

        <StyledPaper flex={2}>
          <SectionHeader>
            <Typography variant="h6" sx={{ fontWeight: 'bold' }}>
              Ongoing Tasks
            </Typography>
            <Divider sx={{ mb: 0 }} />
          </SectionHeader>
          <SectionContent>
            <OngoingTasksList memberId={301} />
          </SectionContent>
        </StyledPaper>
      </ContentSection>

      <StyledPaper>
        <SectionHeader>
          <Typography variant="h6" sx={{ fontWeight: 'bold' }}>
            Agile Notes
          </Typography>
          <Divider sx={{ mb: 0 }} />
        </SectionHeader>
        <SectionContent>
          {[...Array(10)].map((_, index) => (
            // eslint-disable-next-line react/no-array-index-key
            <Typography key={index}>
              여기에 중요한 노트나 메모를 작성할 수 있습니다.
            </Typography>
          ))}
        </SectionContent>
      </StyledPaper>
    </MainContent>
  </DashboardContainer>
);

export default DashboardPage;

const DashboardContainer = styled.div`
  display: flex;
  height: 100vh;
  overflow: hidden;
`;

const FixedSideMenu = styled.div`
  position: fixed;
  top: 76px;
  left: 0;
  height: calc(100vh - 76px);
  z-index: 900;
`;

const MainContent = styled.div`
  position: fixed;
  top: 76px;
  left: 240px;
  width: calc(100vw - 240px);
  height: calc(100vh - 76px);
  background-color: #f5f8ff;
  padding: 32px;
  overflow-y: auto;
`;

const HeaderContainer = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 40px;
`;

const ProjectInfo = styled.div`
  max-width: 60%;
  overflow: hidden;
  text-overflow: ellipsis;
`;

const ExternalLinkContainer = styled.div`
  margin-left: auto;
  flex-shrink: 0;
`;

const ContentSection = styled.div`
  display: flex;
  gap: 24px;
  margin-bottom: 24px;
`;

const StyledPaper = styled.div`
  flex: ${(props) => props.flex || 1};
  background-color: #fff;
  border-radius: 12px;
  height: 30vh;
  display: flex;
  flex-direction: column;
  overflow: hidden;
`;

const SectionHeader = styled.div`
  padding: 16px;
  padding-bottom: 0;
  position: sticky;
  top: 0;
  background-color: #fff;
  z-index: 1;
`;

const SectionContent = styled.div`
  padding: 16px;
  padding-top: 8px;
  overflow-y: auto;
  flex-grow: 1;
`;
