import React from 'react';
import styled from 'styled-components';
import { Info, Edit, Delete } from '@mui/icons-material';
import mockProjects from '../../../mocks/mockProjects';

const ProjectList = () => (
  <Container>
    {mockProjects.map((project) => (
      <ProjectItem key={project.id}>
        <ProjectTitle>- {project.title}</ProjectTitle>

        <IconGroup>
          <IconButton color="#0eaaf9" aria-label="view">
            <Info fontSize="small" />
          </IconButton>
          <IconButton color="#43a047" aria-label="edit">
            <Edit fontSize="small" />
          </IconButton>
          <IconButton color="#e53935" aria-label="delete">
            <Delete fontSize="small" />
          </IconButton>
        </IconGroup>
      </ProjectItem>
    ))}
  </Container>
);

export default ProjectList;

const Container = styled.div``;

const ProjectItem = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 8px;
`;

const ProjectTitle = styled.span`
  font-size: 1.2rem;
  color: #333;
`;

const IconGroup = styled.div`
  display: flex;
  gap: 4px;
`;

const IconButton = styled.button`
  background: none;
  border: none;
  color: ${(props) => props.color};
  cursor: pointer;
  padding: 4px;

  &:hover {
    opacity: 0.8;
  }
`;
